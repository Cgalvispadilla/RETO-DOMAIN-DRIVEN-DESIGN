package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.commands.ActualizarCliente;


public class ActualizarClienteUseCase extends UseCase<RequestCommand<ActualizarCliente>, ResponseEvents>{

    @Override
    public void executeUseCase(RequestCommand<ActualizarCliente> actualizarClienteRequestCommand) {
        var command = actualizarClienteRequestCommand.getCommand();
        var venta = Venta.from(command.getVentaID(),retrieveEvents(command.getVentaID().value()));
        venta.ActualizarCliente(command.getNombre(), command.getNumeroCelular(), command.getDireccion(), command.getEmail());
        emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
    }
}