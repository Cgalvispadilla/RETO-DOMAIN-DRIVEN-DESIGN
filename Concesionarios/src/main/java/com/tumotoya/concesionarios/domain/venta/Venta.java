package com.tumotoya.concesionarios.domain.venta;

import co.com.sofka.domain.generic.AggregateEvent;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class Venta extends AggregateEvent<VentaID> {
    protected Fecha fecha;
    protected MetodoDePago metodoDePago;
    public Venta(VentaID entityId, MetodoDePago metodoDePago) {
        super(entityId);

    }
}
