package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.*;
import com.tumotoya.concesionarios.domain.venta.commands.AgregarDetalle;
import com.tumotoya.concesionarios.domain.venta.events.DetalleAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ModelExtensionsKt;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgregarDetalleUseCaseTest {
    private static final String DETALLE_ID = "D-1111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de agregar un detalle de venta en una venta")
    void agregarClienteEnVenta() {
        //arrange
        var command = new AgregarDetalle(
                VentaID.of("V-1111"),
                new DetalleID(DETALLE_ID),
                new Placa("XY1 K11"),
                new CantidadProducto(1)
        );
        var useCase = new AgregarDetalleUseCase();
        Mockito.when(repository.getEventsBy(DETALLE_ID)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(DETALLE_ID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventDetalleAgregado = (DetalleAgregado) events.get(0);
        Assertions.assertEquals(DETALLE_ID, eventDetalleAgregado.getEntityId().value());
        Assertions.assertEquals("XY1 K11", eventDetalleAgregado.getPlaca().value());
        Assertions.assertEquals(1, command.getCantidadProducto().value());
        Mockito.verify(repository).getEventsBy(DETALLE_ID);
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