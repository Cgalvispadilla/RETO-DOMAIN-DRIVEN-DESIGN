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
import com.tumotoya.concesionarios.domain.venta.events.ClienteAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
@ExtendWith(MockitoExtension.class)
class AgregarClienteUseCaseTest {
    private static final String IDCLIENTE="1111-1111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de agregar un cliente en una venta")
    void agregarClienteEnVenta(){
        //arrange
        var command = new AgregarCliente(VentaID.of("1111"),
                new ClienteID(IDCLIENTE),
                new Nombre("Carlos"),
                new NumeroCelular("3116989942"),
                new Direccion("Barrio el prado"),
                new Email("Fktcg99@gmail.com")
        );
        var useCase= new AgregarClienteUseCase();
        Mockito.when(repository.getEventsBy(IDCLIENTE)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events= UseCaseHandler.getInstance()
                .setIdentifyExecutor(IDCLIENTE)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventClienteAgregado = (ClienteAgregado) events.get(0);
        Assertions.assertEquals("Carlos", eventClienteAgregado.getNombre().value());
        Assertions.assertEquals(IDCLIENTE, eventClienteAgregado.getClienteID().value());
        Assertions.assertEquals("3116989942", eventClienteAgregado.getNumeroCelular().value());
        Assertions.assertEquals("Fktcg99@gmail.com", eventClienteAgregado.getEmail().value());
        Assertions.assertEquals("Barrio el prado", eventClienteAgregado.getDireccion().value());
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