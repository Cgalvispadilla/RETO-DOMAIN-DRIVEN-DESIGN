package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.concesionario.Concesionario;
import com.tumotoya.concesionarios.domain.concesionario.commands.ActualizarMoto;

public class ActualizarMotoUseCase extends UseCase<RequestCommand<ActualizarMoto>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ActualizarMoto> actualizarMotoRequestCommand) {
        var command = actualizarMotoRequestCommand.getCommand();
        var concesionario = Concesionario.from(command.getConcesionarioID(),retrieveEvents(command.getPlaca().value()));
        concesionario.actualizarMoto(command.getPlaca(), command.getNombre(), command.getMarca(), command.getModelo(), command.getCilindraje(), command.getValor());
        emit().onResponse(new ResponseEvents(concesionario.getUncommittedChanges()));
    }
}
