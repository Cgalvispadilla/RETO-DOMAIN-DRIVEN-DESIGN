package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.commands.ActualizarCantidadProductoDetalle;

public class ActualizarCantidadProductoDetalleUseCase extends UseCase<RequestCommand<ActualizarCantidadProductoDetalle>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ActualizarCantidadProductoDetalle> actualizarCantidadProductoDetalleRequestCommand) {
        var command = actualizarCantidadProductoDetalleRequestCommand.getCommand();
        var venta = Venta.from(command.getVentaID(), retrieveEvents(command.getEntityId().value()));
        venta.actualizarCantidadProductoDetalle(command.getEntityId(), command.getCantidadProducto());
        emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
    }
}
