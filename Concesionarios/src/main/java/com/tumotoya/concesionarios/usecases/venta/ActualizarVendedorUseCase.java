package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.commands.ActualizarVendedor;


public class ActualizarVendedorUseCase extends UseCase<RequestCommand<ActualizarVendedor>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<ActualizarVendedor> actualizarVendedorRequestCommand) {
        var command = actualizarVendedorRequestCommand.getCommand();
        var venta = Venta.from(command.getVentaID(),retrieveEvents(command.getVendedorID().value()));
        venta.actualizarVendedor(command.getVendedorID(), command.getNombre(), command.getNumeroCelular(), command.getDireccion());
        emit().onResponse( new ResponseEvents(venta.getUncommittedChanges()));
    }
}
