package com.tumotoya.concesionarios.domain.concesionario.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;

public class ConcesionarioCreado extends DomainEvent {
    private final Nombre nombre;

    public ConcesionarioCreado(Nombre nombre) {
        super("concesionarios.concesionario.concesionariocreado");
        this.nombre = nombre;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
