package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;

public class DescuentoPorPagoConTarjetaAsignado extends DomainEvent {
    private final Double descuento;

    public DescuentoPorPagoConTarjetaAsignado(Double descuento) {
        super("concesionarios.concesionario.descuentoporpagocontarjetaasignado");
        this.descuento = descuento;
    }

    public Double getDescuento() {
        return descuento;
    }
}
