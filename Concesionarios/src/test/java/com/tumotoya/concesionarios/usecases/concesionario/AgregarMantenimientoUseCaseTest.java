package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarMantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.events.MantenimientoAgregado;
import com.tumotoya.concesionarios.domain.concesionario.values.*;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
@ExtendWith(MockitoExtension.class)
class AgregarMantenimientoUseCaseTest {
    private static final String ID_MANTENIMIENTO = "M-1111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de agregar un matenimiento en un concesionario")
    void agregarMantenimientoEnConcesionario() {
        //arrange
        var command = new AgregarMantenimiento(
                ConcesionarioID.of("C-1111"),
                new MantenimientoID(ID_MANTENIMIENTO),
                new Nombre("Cambio de aceite"),
                new Valor("15000")
        );
        var useCase = new AgregarMantenimientoUseCase();
        Mockito.when(repository.getEventsBy(ID_MANTENIMIENTO)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(ID_MANTENIMIENTO)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventMatenimientoAgregado = (MantenimientoAgregado) events.get(0);
        Assertions.assertEquals(ID_MANTENIMIENTO, eventMatenimientoAgregado.getMantenimientoID().value());
        Assertions.assertEquals("Cambio de aceite", eventMatenimientoAgregado.getNombre().value());
        Assertions.assertEquals("15000", eventMatenimientoAgregado.getValor().value());
        Mockito.verify(repository).getEventsBy(ID_MANTENIMIENTO);
    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new ConcesionarioCreado(
                        new Nombre("Concesionario Sofka")
                )
        );
    }
}