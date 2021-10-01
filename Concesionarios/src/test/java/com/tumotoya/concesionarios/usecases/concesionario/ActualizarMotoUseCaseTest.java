package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.commands.ActualizarMoto;
import com.tumotoya.concesionarios.domain.concesionario.events.*;
import com.tumotoya.concesionarios.domain.concesionario.values.*;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
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
class ActualizarMotoUseCaseTest {
    private final String PLACA = "M-111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de actualizar una moto de un concesionario")
    void actuarlizarMotoDeUnConcesionarioDeManeraEsperada() {
        //arrange
        var command = new ActualizarMoto(
                ConcesionarioID.of("C-1234"),
                new Placa(PLACA),
                new Nombre("Boxer"),
                new Marca("BAJAJ"),
                new Modelo("2022"),
                new Cilindraje("100"),
                new Valor("4000000")
        );
        var useCase = new ActualizarMotoUseCase();
        Mockito.when(repository.getEventsBy(Mockito.any())).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(PLACA)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventMotoActualizada = (MotoActualizada) events.get(0);
        Assertions.assertEquals("BOXER", eventMotoActualizada.getNombre().value());
        Assertions.assertEquals("BAJAJ", eventMotoActualizada.getMarca().value());
        Assertions.assertEquals("2022", eventMotoActualizada.getCilindraje().value());
        Assertions.assertEquals("100", eventMotoActualizada.getCilindraje().value());
        Assertions.assertEquals("4000000", eventMotoActualizada.getValor().value());

    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new ConcesionarioCreado(
                        new Nombre("Mi concesionario")
                ),
                new MotoAgregada(
                        new Placa(PLACA),
                        new Nombre("Boxererr"),
                        new Marca("BAJAJJJJ"),
                        new Modelo("2022222"),
                        new Cilindraje("1000"),
                        new Valor("40000000")
                )
        );
    }

}