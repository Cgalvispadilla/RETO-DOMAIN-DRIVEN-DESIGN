package com.tumotoya.concesionarios.usecases.venta.usecasesevents;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.events.DetalleAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.CostoTotal;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class AsignarBonoUseCase extends UseCase<TriggeredEvent<VentaCreada>, ResponseEvents> {
    private static final Double BONO=400000D;
    @Override
    public void executeUseCase(TriggeredEvent<VentaCreada> ventaCreadaTriggeredEvent) {
        var event = ventaCreadaTriggeredEvent.getDomainEvent();
        var venta= Venta.from(VentaID.of(event.aggregateRootId()),this.retrieveEvents());
        if(venta.costoTotal().value()>5000000){
            venta.asignarBono(BONO);
            venta.calcularCostoTotal(new CostoTotal(venta.costoTotal().value()-BONO));
            emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
        }
    }
}
