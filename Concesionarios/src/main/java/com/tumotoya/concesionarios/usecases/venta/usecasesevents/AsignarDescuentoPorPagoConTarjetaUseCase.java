package com.tumotoya.concesionarios.usecases.venta.usecasesevents;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.CostoTotal;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;


public class AsignarDescuentoPorPagoConTarjetaUseCase extends UseCase<TriggeredEvent<VentaCreada>, ResponseEvents> {
    private static final double PORCENTAJE_DESCUENTO = 0.025;

    @Override
    public void executeUseCase(TriggeredEvent<VentaCreada> ventaCreadaTriggeredEvent) {
        var event = ventaCreadaTriggeredEvent.getDomainEvent();
        var venta = Venta.from(VentaID.of(event.aggregateRootId()), this.retrieveEvents());
        if (venta.metodoDePago().equals(MetodoDePago.TARJETA)&&venta.costoTotal().value()>=6000000D) {
            venta.asignarDescuentoPorPagoConTarjeta(PORCENTAJE_DESCUENTO);
            var descuento = venta.costoTotal().value() * PORCENTAJE_DESCUENTO;
            venta.calcularCostoTotal(new CostoTotal(venta.costoTotal().value() - descuento));
            emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
        }
    }
}
