package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;

public class VentaCreada extends DomainEvent {
    private final Fecha fecha;
    private final MetodoDePago metodoDePago;

    public VentaCreada(Fecha fecha, MetodoDePago metodoDePago) {
        super("concesionarios.concesionario.ventacreada");
        this.fecha = fecha;
        this.metodoDePago = metodoDePago;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }
}
