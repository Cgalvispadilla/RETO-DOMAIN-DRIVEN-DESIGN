package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.commands.ActualizarValorMantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.events.MantenimientoAgregado;
import com.tumotoya.concesionarios.domain.concesionario.events.ValorMantenimientoActualizado;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.concesionario.values.MantenimientoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Valor;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
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
class ActualizarValorMantenimientoUseCaseTest {
    private final String MANTENIMIENTO_ID="M-123431";
    @Mock
    private DomainEventRepository repository;
    @Test
    @DisplayName("Prueba para validar la actualización del valor que tiene un mantenimiento de un concesionario")
    void validarActualizacionDelValorDeMantenimiento() {
        //arrange
        var command = new ActualizarValorMantenimiento(
                ConcesionarioID.of("C-1234"),
                new MantenimientoID(MANTENIMIENTO_ID),
                new Valor("300000")
        );
        var useCase = new ActualizarValorMantenimientoUseCase();
        Mockito.when(repository.getEventsBy(MANTENIMIENTO_ID)).thenReturn(eventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(MANTENIMIENTO_ID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventValorDetalleActualizado = (ValorMantenimientoActualizado) events.get(0);
        Assertions.assertEquals(MANTENIMIENTO_ID, eventValorDetalleActualizado.getMantenimientoID().value());
        Assertions.assertEquals("300000",eventValorDetalleActualizado.getValor().value());
        Mockito.verify(repository).getEventsBy(MANTENIMIENTO_ID);
    }

    private List<DomainEvent> eventStored() {
        var eventConcesionarioCreado = new ConcesionarioCreado(
                new Nombre("Mi concesionario")
        );
        var eventMantenimientoCreado = new MantenimientoAgregado(
                new MantenimientoID(MANTENIMIENTO_ID),
                new Nombre("Tecnomecanica"),
                new Valor("200000")
        );
        return List.of(
                eventConcesionarioCreado,
                eventMantenimientoCreado
        );
    }
}