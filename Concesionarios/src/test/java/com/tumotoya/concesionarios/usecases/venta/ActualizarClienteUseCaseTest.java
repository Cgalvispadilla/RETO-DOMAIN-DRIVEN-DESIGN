package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.commands.ActualizarCliente;
import com.tumotoya.concesionarios.domain.venta.events.*;
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
class ActualizarClienteUseCaseTest {
    private final String CLIENTE_ID = "C-111";
    private final String VENTA_ID= "V-111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de actualizar un cliente en una venta")
    void actuarlizarClienteEnVentaDeManeraEsperada() {
        //arrange
        var command = new ActualizarCliente(
                VentaID.of(VENTA_ID),
                new Nombre("Andres Galvis"),
                new NumeroCelular("3116989942"),
                new Direccion("En algun lugar feliz vive"),
                new Email("qwere@gmail.com")
        );
        var useCase = new ActualizarClienteUseCase();
        Mockito.when(repository.getEventsBy(VENTA_ID)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(VENTA_ID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventClienteActualizado = (ClienteActualizado) events.get(0);
        Assertions.assertEquals("Andres Galvis", eventClienteActualizado.getNombre().value());
        Assertions.assertEquals("3116989942", eventClienteActualizado.getNumeroCelular().value());
        Assertions.assertEquals("En algun lugar feliz vive", eventClienteActualizado.getDireccion().value());
        Mockito.verify(repository).getEventsBy(VENTA_ID);
    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new VentaCreada(
                        new Fecha(),
                        MetodoDePago.EFECTIVO
                ),
                new ClienteAgregado(
                        new ClienteID(CLIENTE_ID),
                        new Nombre("Jesus"),
                        new NumeroCelular("1111111111"),
                        new Direccion("Algun lugar por ahí"),
                        new Email("unmalcorreo@gmail.com")
                )
        );
    }


}