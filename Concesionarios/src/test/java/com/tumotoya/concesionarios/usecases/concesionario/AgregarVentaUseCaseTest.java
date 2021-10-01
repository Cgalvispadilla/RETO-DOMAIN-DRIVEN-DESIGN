package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarVenta;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.events.MotoAgregada;
import com.tumotoya.concesionarios.domain.concesionario.events.VentaAgregada;
import com.tumotoya.concesionarios.domain.concesionario.values.*;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
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
class AgregarVentaUseCaseTest {
    private static final String VENTA_ID = "V-1111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de agregar una venta en un concesionario")
    void agregarVentaEnConcesionario() {
        //arrange
        var command = new AgregarVenta(
                ConcesionarioID.of("C-1111"),
                new VentaID(VENTA_ID)
        );
        var useCase = new AgregarVentaUseCase();
        Mockito.when(repository.getEventsBy(VENTA_ID)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(VENTA_ID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventVentaAgregado = (VentaAgregada) events.get(0);
        Assertions.assertEquals(VENTA_ID, eventVentaAgregado.getVentaID().value());
        Mockito.verify(repository).getEventsBy(VENTA_ID);

    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new ConcesionarioCreado(
                        new Nombre("Concesionario CG")
                )
        );
    }

}