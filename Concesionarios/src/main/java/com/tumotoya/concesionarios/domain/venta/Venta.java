package com.tumotoya.concesionarios.domain.venta;

import co.com.sofka.domain.generic.AggregateEvent;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class Venta extends AggregateEvent<VentaID> {
    public Venta(VentaID entityId) {
        super(entityId);
    }
}
