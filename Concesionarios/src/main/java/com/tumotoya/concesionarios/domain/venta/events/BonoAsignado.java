package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;

public class BonoAsignado extends DomainEvent {
    private final Double bono;

    public BonoAsignado(Double bono) {
        super("concesionarios.concesionario.bonoasignado");
        this.bono = bono;
    }

    public Double getBono() {
        return bono;
    }
}
