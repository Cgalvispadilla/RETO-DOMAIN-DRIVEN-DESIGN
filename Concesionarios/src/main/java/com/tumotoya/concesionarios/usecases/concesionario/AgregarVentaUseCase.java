package com.tumotoya.concesionarios.usecases.concesionario;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.concesionario.Concesionario;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarVenta;

public class AgregarVentaUseCase extends UseCase<RequestCommand<AgregarVenta>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarVenta> agregarVentaRequestCommand) {
        var command = agregarVentaRequestCommand.getCommand();
        var concesionario= Concesionario.from(command.getConcesionarioID(),retrieveEvents(command.getVentaID().value()));
        concesionario.agregarVenta(command.getVentaID());
        emit().onResponse(new ResponseEvents(concesionario.getUncommittedChanges()));
    }
}
