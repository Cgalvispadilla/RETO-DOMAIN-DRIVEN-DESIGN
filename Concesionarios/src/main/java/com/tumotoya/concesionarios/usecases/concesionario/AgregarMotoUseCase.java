package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.concesionario.Concesionario;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarMoto;

public class AgregarMotoUseCase extends UseCase<RequestCommand<AgregarMoto>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarMoto> agregarMotoRequestCommand) {
        var command = agregarMotoRequestCommand.getCommand();
        var concesionario = Concesionario.from(command.getConcesionarioID(), retrieveEvents(command.getPlaca().value()));
        concesionario.agregarMoto(
                command.getPlaca(),
                command.getNombre(),
                command.getMarca(),
                command.getModelo(),
                command.getCilindraje(),
                command.getValor()
        );
        emit().onResponse(new ResponseEvents(concesionario.getUncommittedChanges()));
    }
}
