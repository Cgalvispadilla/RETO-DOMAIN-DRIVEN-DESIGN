package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.venta.commands.CalcularCostoTotal;
import com.tumotoya.concesionarios.domain.venta.events.CostoTotalCalculado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.CostoTotal;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalcularCostoTotalUseCaseTest {
    private static final String ID_VENTA = "V-111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad del calcular el costo total en una venta")
    void calcularCostoTotalDeUnaVenta() {
        //arrange
        var command = new CalcularCostoTotal(
                new VentaID(ID_VENTA),
                new CostoTotal(13030010D)
        );
        var useCase = new CalcularCostoTotalUseCase();
        Mockito.when(repository.getEventsBy(ID_VENTA)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(ID_VENTA)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventCostoTotalActualizado = (CostoTotalCalculado) events.get(0);
        Assertions.assertEquals(13030010D, eventCostoTotalActualizado.getCostoTotal().value());
        Mockito.verify(repository).getEventsBy(ID_VENTA);
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