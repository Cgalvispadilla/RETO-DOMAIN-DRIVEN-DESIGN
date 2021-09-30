package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.commands.AgregarCliente;



public class AgregarClienteUseCase extends UseCase<RequestCommand<AgregarCliente>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AgregarCliente> agregarClienteRequestCommand) {
        var command = agregarClienteRequestCommand.getCommand();
        var venta = Venta.from(command.getVentaID(), retrieveEvents(command.getClienteId().value()));
        venta.agregarCliente(command.getClienteId(), command.getNombre(), command.getNumeroCelular(), command.getDireccion(), command.getEmail());
        emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
    }
}
