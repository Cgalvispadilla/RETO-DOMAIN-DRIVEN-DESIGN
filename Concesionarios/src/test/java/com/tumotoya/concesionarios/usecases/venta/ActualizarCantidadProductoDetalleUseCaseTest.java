package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.generics.values.Placa;
import com.tumotoya.concesionarios.domain.venta.commands.ActualizarCantidadProductoDetalle;
import com.tumotoya.concesionarios.domain.venta.commands.ActualizarVendedor;
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
class ActualizarCantidadProductoDetalleUseCaseTest {
    private final static String DETALLE_ID="D-111";

    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de actualizar la cantiadad de producto en el detalle de una venta")
    void actualizarCantidadProductoEnElDetalleDeUnaVenta(){
        //arrange
        var command = new ActualizarCantidadProductoDetalle(
                new VentaID("V-111"),
                new DetalleID(DETALLE_ID),
                new CantidadProducto(4)
        );
        var useCase= new ActualizarCantidadProductoDetalleUseCase();
        Mockito.when(repository.getEventsBy(Mockito.any())).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events= UseCaseHandler.getInstance()
                .setIdentifyExecutor(DETALLE_ID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventCantidadProductoDetalleActualizado = (CantidadProductoDetalleActualizado) events.get(0);
        Assertions.assertEquals(4, eventCantidadProductoDetalleActualizado.getCantidadProducto().value());

    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new VentaCreada(
                        new Fecha(),
                        MetodoDePago.EFECTIVO
                ),
                new DetalleAgregado(
                        new DetalleID(DETALLE_ID),
                        new Placa("1111"),
                        new CantidadProducto(1)
                )
        );
    }}