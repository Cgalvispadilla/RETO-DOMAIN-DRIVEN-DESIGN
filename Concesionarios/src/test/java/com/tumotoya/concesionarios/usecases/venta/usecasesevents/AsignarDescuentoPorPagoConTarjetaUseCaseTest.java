package com.tumotoya.concesionarios.usecases.venta.usecasesevents;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.venta.events.CostoTotalCalculado;
import com.tumotoya.concesionarios.domain.venta.events.DescuentoPorPagoConTarjetaAsignado;
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
class AsignarDescuentoPorPagoConTarjetaUseCaseTest {
    private static final String VENTAID = "VV-1111";
    private static final double PORCENTAJE_DESCUENTO = 0.025;

    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Prueba para validar la asignacion de un descuento cuando una venta supere 6 millones y pague por tarjeta")
    void asignarDescuentoPorPagoConTarjeta() {
        //arrange
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.TARJETA
        );
        event.setAggregateRootId(VENTAID);

        var useCase = new AsignarDescuentoPorPagoConTarjetaUseCase();
        Mockito.when(repository.getEventsBy(VENTAID)).thenReturn(eventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(VENTAID)
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();


        //assert
        var eventDescuentoCreado = (DescuentoPorPagoConTarjetaAsignado) events.get(0);
        var eventDescuentoAsignado = (CostoTotalCalculado) events.get(1);
        Assertions.assertEquals(PORCENTAJE_DESCUENTO, eventDescuentoCreado.getDescuento());
        Assertions.assertEquals(6825000, eventDescuentoAsignado.getCostoTotal().value());
        Mockito.verify(repository).getEventsBy(VENTAID);

    }
    @Test
    @DisplayName("Prueba para validar la no asignacion de un descuento cuando una venta supere 6 millones y pague por tarjeta")
    void noAsignaDescuentoPorPagoConTarjeta() {
        //arrange
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.TARJETA
        );
        event.setAggregateRootId(VENTAID);

        var useCase = new AsignarDescuentoPorPagoConTarjetaUseCase();
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
    private List<DomainEvent> eventStoredFail() {
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.TARJETA
        );
        var eventCalcularCostoTotal = new CostoTotalCalculado(
                new CostoTotal(700000D)
        );
        return List.of(
                event,
                eventCalcularCostoTotal
        );
    }
    private List<DomainEvent> eventStored() {
        var event = new VentaCreada(
                new Fecha(),
                MetodoDePago.TARJETA
        );
        var eventCalcularCostoTotal = new CostoTotalCalculado(
                new CostoTotal(7000000D)
        );
        return List.of(
                event,
                eventCalcularCostoTotal
        );
    }
}