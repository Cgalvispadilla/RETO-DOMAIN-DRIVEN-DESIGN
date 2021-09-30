package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.concesionario.Concesionario;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarMantenimiento;

public class AgregarMantenimientoUseCase extends UseCase<RequestCommand<AgregarMantenimiento>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarMantenimiento> agregarMantenimientoRequestCommand) {
        var command = agregarMantenimientoRequestCommand.getCommand();
        var concesionario = Concesionario.from(command.getConcesionarioID(),retrieveEvents(command.getMantenimientoID().value()));
        concesionario.agregarMantenimiento(
                command.getMantenimientoID(),
                command.getNombre(),
                command.getValor()
        );
        emit().onResponse(new ResponseEvents(concesionario.getUncommittedChanges()));
    }
}
