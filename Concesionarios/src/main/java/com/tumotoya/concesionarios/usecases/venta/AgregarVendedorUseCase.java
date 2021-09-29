package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.commands.AgregarVendedor;

public class AgregarVendedorUseCase extends UseCase<RequestCommand<AgregarVendedor>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AgregarVendedor> vendedorAgregadoRequestCommand) {
        var command = vendedorAgregadoRequestCommand.getCommand();
        var venta = Venta.from(command.getVentaID(),retrieveEvents(command.getVendedorId().value()));
        venta.agregarVendedor(command.getVendedorId(),command.getNombre(), command.getNumeroCelular(), command.getDireccion());
        emit().onResponse( new ResponseEvents(venta.getUncommittedChanges()));
    }
}
