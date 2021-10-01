package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.concesionario.Concesionario;
import com.tumotoya.concesionarios.domain.concesionario.commands.ActualizarEmpleado;

public class ActualizarEmpleadoUseCase extends UseCase<RequestCommand<ActualizarEmpleado>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ActualizarEmpleado> actualizarEmpleadoRequestCommand) {
        var command = actualizarEmpleadoRequestCommand.getCommand();
        var concesionario = Concesionario.from(command.getConcesionarioID(), retrieveEvents(command.getEmpleadoID().value()));
        concesionario.actualizarEmpleado(
                command.getEmpleadoID(),
                command.getNombre(),
                command.getNumeroCelular(),
                command.getDireccion(),
                command.getRol()
        );
        emit().onResponse(new ResponseEvents(concesionario.getUncommittedChanges()));
    }
}
