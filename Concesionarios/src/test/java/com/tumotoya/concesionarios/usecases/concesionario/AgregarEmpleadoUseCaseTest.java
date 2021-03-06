package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarEmpleado;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.events.EmpleadoAgregado;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.concesionario.values.EmpleadoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Rol;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AgregarEmpleadoUseCaseTest {
    private static final String ID_EMPLEADO = "E-1111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de agregar un empleado en un concesionario")
    void agregarEmpleadoEnConcesionario() {
        //arrange
        var command = new AgregarEmpleado(
                ConcesionarioID.of("C-1111"),
                new EmpleadoID(ID_EMPLEADO),
                new Nombre("Carlos Galvis"),
                new NumeroCelular("3116989942"),
                new Direccion("En algun lugar feliz"),
                new Rol("Tengo un rol, tengo una responsabilidad")
        );
        var useCase = new AgregarEmpleadoUseCase();
        Mockito.when(repository.getEventsBy(ID_EMPLEADO)).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(ID_EMPLEADO)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventEmpleadoAgregado = (EmpleadoAgregado) events.get(0);
        Assertions.assertEquals("Carlos Galvis", eventEmpleadoAgregado.getNombre().value());
        Assertions.assertEquals(ID_EMPLEADO, eventEmpleadoAgregado.getEntityId().value());
        Assertions.assertEquals("3116989942", eventEmpleadoAgregado.getNumeroCelular().value());
        Assertions.assertEquals("En algun lugar feliz", eventEmpleadoAgregado.getDireccion().value());
        Assertions.assertEquals("Tengo un rol, tengo una responsabilidad", eventEmpleadoAgregado.getRol().value());
        Mockito.verify(repository).getEventsBy(ID_EMPLEADO);
    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new ConcesionarioCreado(
                        new Nombre("Concesionario YASTA")
                )
        );
    }


}