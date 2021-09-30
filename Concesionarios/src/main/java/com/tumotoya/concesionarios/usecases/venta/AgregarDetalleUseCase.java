package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.commands.AgregarDetalle;

public class AgregarDetalleUseCase extends UseCase<RequestCommand<AgregarDetalle>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarDetalle> agregarDetalleRequestCommand) {
        var command = agregarDetalleRequestCommand.getCommand();
        var venta = Venta.from(command.getVentaID(),retrieveEvents(command.getDetalleID().value()));
        venta.agregarDetalle(command.getDetalleID(), command.getPlaca(), command.getCantidadProducto());
        emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
    }
}
