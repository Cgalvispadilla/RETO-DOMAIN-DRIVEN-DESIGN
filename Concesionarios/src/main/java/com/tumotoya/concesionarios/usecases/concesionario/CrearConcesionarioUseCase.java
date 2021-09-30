package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.concesionario.Concesionario;
import com.tumotoya.concesionarios.domain.concesionario.commands.CrearConcesionario;

public class CrearConcesionarioUseCase extends UseCase<RequestCommand<CrearConcesionario>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearConcesionario> crearConcesionarioRequestCommand) {
        var command = crearConcesionarioRequestCommand.getCommand();
        var concesionario = new Concesionario(
                command.getEntityId(),
                command.getNombre()
        );
        emit().onResponse(new ResponseEvents(concesionario.getUncommittedChanges()));
    }
}
