package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.commands.ActualizarEmpleado;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.events.EmpleadoActualizado;
import com.tumotoya.concesionarios.domain.concesionario.events.EmpleadoAgregado;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.concesionario.values.EmpleadoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Rol;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.commands.ActualizarCliente;
import com.tumotoya.concesionarios.domain.venta.events.ClienteActualizado;
import com.tumotoya.concesionarios.domain.venta.events.ClienteAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;
import com.tumotoya.concesionarios.usecases.venta.ActualizarClienteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ActualizarEmpleadoUseCaseTest {
    private final String EMPLEADO_ID = "E-111";
    @Mock
    private DomainEventRepository repository;

    @Test
    @DisplayName("Esta prueba valida la funcionalidad a la hora de actualizar un empleado en un concesionario")
    void actuarlizarEmpleadoDeUnConcesionarioDeManeraEsperada() {
        //arrange
        var command = new ActualizarEmpleado( ConcesionarioID.of("C-3131"),
                new EmpleadoID(EMPLEADO_ID),
                new Nombre("Carlos A Galvis"),
                new NumeroCelular("3116989942"),
                new Direccion("Pueblo nuevo"),
                new Rol("Un rol"));
        var useCase = new ActualizarEmpleadoUseCase();
        Mockito.when(repository.getEventsBy(Mockito.any())).thenReturn(EventStored());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(EMPLEADO_ID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var eventEmpleadoActualizado = (EmpleadoActualizado) events.get(0);
        Assertions.assertEquals("Carlos A Galvis", eventEmpleadoActualizado.getNombre().value());
        Assertions.assertEquals("3116989942", eventEmpleadoActualizado.getNumeroCelular().value());
        Assertions.assertEquals("Pueblo nuevo", eventEmpleadoActualizado.getDireccion().value());
        Assertions.assertEquals("Un rol", eventEmpleadoActualizado.getRol().value());

    }

    private List<DomainEvent> EventStored() {
        return List.of(
                new ConcesionarioCreado(
                        new Nombre("Mi concesionario")
                ),
                new EmpleadoAgregado(
                        new EmpleadoID(EMPLEADO_ID),
                        new Nombre("Jesus"),
                        new NumeroCelular("1111111111"),
                        new Direccion("Algun lugar por ahí"),
                        new Rol("Jefe")
                )
        );
    }


}
