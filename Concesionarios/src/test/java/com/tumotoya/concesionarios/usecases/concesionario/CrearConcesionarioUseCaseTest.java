package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.tumotoya.concesionarios.domain.concesionario.commands.CrearConcesionario;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.venta.commands.CrearVenta;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;
import com.tumotoya.concesionarios.usecases.venta.CrearVentaUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CrearConcesionarioUseCaseTest {
    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de crear un concesionario")
    void crearVenta() {
        //arrange
        var command = new CrearConcesionario(
                ConcesionarioID.of("C-1111"),
                new Nombre("Lleva tu motora")
        );
        var useCase = new CrearConcesionarioUseCase();

        //act
        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (ConcesionarioCreado) events.get(0);
        Assertions.assertEquals("Lleva tu motora", event.getNombre().value());
    }
}