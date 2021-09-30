package com.tumotoya.concesionarios.usecases.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.commands.CalcularCostoTotal;

public class CalcularCostoTotalUseCase extends UseCase<RequestCommand<CalcularCostoTotal>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CalcularCostoTotal> calcularCostoTotalRequestCommand) {
        var command = calcularCostoTotalRequestCommand.getCommand();
        var venta = Venta.from(command.getVentaID(), retrieveEvents(command.getVentaID().value()));
        venta.calcularCostoTotal(command.getCostoTotal());
        emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
    }
}
