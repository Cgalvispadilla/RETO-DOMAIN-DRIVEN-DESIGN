package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarMantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarMoto;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.events.MantenimientoAgregado;
import com.tumotoya.concesionarios.domain.concesionario.events.MotoAgregada;
import com.tumotoya.concesionarios.domain.concesionario.values.*;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.Placa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
@ExtendWith(MockitoExtension.class)
class AgregarMotoUseCaseTest {
    private static final String PLACA = "OSI-29B";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de agregar una moto en un concesionario")
    void agregarMotoEnConcesionario() {
        //arrange
        var command = new AgregarMoto(
                ConcesionarioID.of("C-1111"),
                new Placa(PLACA),
                new Nombre("CB160"),
                new Marca("Honda"),
                new Modelo("2022"),
                new Cilindraje("160"),
                new Valor("8500000")
        );
        var useCase = new AgregarMotoUseCase();
        Mockito.when(repository.getEventsBy(PLACA)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(PLACA)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventMatenimientoAgregado = (MotoAgregada) events.get(0);
        Assertions.assertEquals(PLACA, eventMatenimientoAgregado.getPlaca().value());
        Assertions.assertEquals("CB160", eventMatenimientoAgregado.getNombre().value());
        Assertions.assertEquals("Honda", eventMatenimientoAgregado.getMarca().value());
        Assertions.assertEquals("2022", eventMatenimientoAgregado.getModelo().value());
        Assertions.assertEquals("160", eventMatenimientoAgregado.getCilindraje().value());
        Assertions.assertEquals("8500000", eventMatenimientoAgregado.getValor().value());
    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new ConcesionarioCreado(
                        new Nombre("Concesionario Sofka U")
                )
        );
    }

}