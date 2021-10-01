package com.tumotoya.concesionarios.domain.concesionario.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class VentaAgregada extends DomainEvent {
    private final VentaID ventaID;

    public VentaAgregada(VentaID ventaID) {
        super("concesionarios.concesionario.ventaagregada");
        this.ventaID = ventaID;
    }

    public VentaID getVentaID() {
        return ventaID;
    }
}
