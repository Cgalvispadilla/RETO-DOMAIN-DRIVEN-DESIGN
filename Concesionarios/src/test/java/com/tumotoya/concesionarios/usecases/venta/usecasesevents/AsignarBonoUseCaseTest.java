package com.tumotoya.concesionarios.usecases.venta.usecasesevents;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.venta.events.BonoAsignado;
import com.tumotoya.concesionarios.domain.venta.events.CostoTotalCalculado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.CostoTotal;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AsignarBonoUseCaseTest {
    private static final String VENTAID = "V-1111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Prueba para validar la asignacion de un bono cuando una venta supere 5 millones")
    void asignarBonoCuandoUnaVentaSobrepase5Millones() {
        //arrange
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.EFECTIVO
        );

        event.setAggregateRootId(VENTAID);
        var useCase = new AsignarBonoUseCase();
        Mockito.when(repository.getEventsBy(VENTAID)).thenReturn(eventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(VENTAID)
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();


        //assert
        var eventBonoCreado = (BonoAsignado) events.get(0);
        var eventBonoAsignado = (CostoTotalCalculado) events.get(1);
        Assertions.assertEquals(400000, eventBonoCreado.getBono());
        Assertions.assertEquals(5600000, eventBonoAsignado.getCostoTotal().value());
        Mockito.verify(repository).getEventsBy(VENTAID);
    }
    @Test
    @DisplayName("Prueba para validar la no asignacion de un bono cuando una venta no supere 5 millones")
    void noAsignacionDeBonoCuandoUnaVentaNoSobrepase5Millones() {
        //arrange
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.EFECTIVO
        );

        event.setAggregateRootId(VENTAID);
        var useCase = new AsignarBonoUseCase();
        Mockito.when(repository.getEventsBy(VENTAID)).thenReturn(eventStoredFail());
        useCase.addRepository(repository);

        Assertions.assertThrows(NoSuchElementException.class,()->{
            var events = UseCaseHandler.getInstance()
                    .setIdentifyExecutor(VENTAID)
                    .syncExecutor(useCase, new TriggeredEvent<>(event))
                    .orElseThrow()
                    .getDomainEvents();
        });
    }

    private List<DomainEvent> eventStored() {
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.EFECTIVO
        );
        var eventCalcularCostoTotal = new CostoTotalCalculado(new CostoTotal(6000000D));
        return List.of(
                event,
                eventCalcularCostoTotal
        );
    }
    private List<DomainEvent> eventStoredFail() {
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.EFECTIVO
        );
        var eventCalcularCostoTotal = new CostoTotalCalculado(new CostoTotal(4000000D));
        return List.of(
                event,
                eventCalcularCostoTotal
        );
    }
}