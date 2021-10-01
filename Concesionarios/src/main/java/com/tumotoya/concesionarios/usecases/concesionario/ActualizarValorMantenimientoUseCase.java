package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.concesionario.Concesionario;
import com.tumotoya.concesionarios.domain.concesionario.commands.ActualizarValorMantenimiento;

public class ActualizarValorMantenimientoUseCase extends UseCase<RequestCommand<ActualizarValorMantenimiento>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ActualizarValorMantenimiento> actualizarValorMantenimientoRequestCommand) {
        var command = actualizarValorMantenimientoRequestCommand.getCommand();
        var concesionario = Concesionario.from(command.getConcesionarioID(), retrieveEvents(command.getMantenimientoID().value()));
        concesionario.actualizarValorMantenimiento(command.getMantenimientoID(), command.getValor());
        emit().onResponse(new ResponseEvents(concesionario.getUncommittedChanges()));
    }
}
