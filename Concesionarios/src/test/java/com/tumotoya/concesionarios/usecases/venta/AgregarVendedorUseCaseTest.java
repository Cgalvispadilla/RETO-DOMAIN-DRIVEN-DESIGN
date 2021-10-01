package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.commands.AgregarCliente;
import com.tumotoya.concesionarios.domain.venta.commands.AgregarVendedor;
import com.tumotoya.concesionarios.domain.venta.events.ClienteAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VendedorAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AgregarVendedorUseCaseTest {
    private static final String ID_VENDEDOR = "1111-1111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de agregar un vendedor en una venta")
    void agregarVendedorEnVenta() {
        //arrange
        var command = new AgregarVendedor(
                VentaID.of("1111"),
                new VendedorID(ID_VENDEDOR),
                new Nombre("Carlos"),
                new NumeroCelular("3116989942"),
                new Direccion("Barrio el prado")
        );
        var useCase = new AgregarVendedorUseCase();
        Mockito.when(repository.getEventsBy(ID_VENDEDOR)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(ID_VENDEDOR)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventVendedorAgregado = (VendedorAgregado) events.get(0);
        Assertions.assertEquals("Carlos", eventVendedorAgregado.getNombre().value());
        Assertions.assertEquals(ID_VENDEDOR, eventVendedorAgregado.getVendedorId().value());
        Assertions.assertEquals("3116989942", eventVendedorAgregado.getNumeroCelular().value());
        Assertions.assertEquals("Barrio el prado", eventVendedorAgregado.getDireccion().value());
        Mockito.verify(repository).getEventsBy(ID_VENDEDOR);
    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new VentaCreada(
                        new Fecha(),
                        MetodoDePago.EFECTIVO
                )
        );
    }


}