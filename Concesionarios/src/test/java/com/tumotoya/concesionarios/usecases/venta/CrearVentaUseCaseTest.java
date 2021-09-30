package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.tumotoya.concesionarios.domain.venta.commands.CrearVenta;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class CrearVentaUseCaseTest {
    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de crear una venta")
    void crearVenta() {
        //arrange
        var command = new CrearVenta(
                VentaID.of("1111"),
                new Fecha(),
                MetodoDePago.EFECTIVO
        );
        var useCase = new CrearVentaUseCase();

        //act
        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (VentaCreada) events.get(0);
        Assertions.assertEquals(LocalDate.now(), event.getFecha().value());
        Assertions.assertEquals("EFECTIVO", event.getMetodoDePago().name());
    }

}