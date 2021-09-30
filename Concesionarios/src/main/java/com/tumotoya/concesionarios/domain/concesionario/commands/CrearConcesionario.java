package com.tumotoya.concesionarios.domain.concesionario.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;

public class CrearConcesionario extends Command {
    private final ConcesionarioID entityId;
    private final Nombre nombre;

    public CrearConcesionario(ConcesionarioID entityId, Nombre nombre) {
        this.entityId = entityId;
        this.nombre = nombre;
    }

    public ConcesionarioID getEntityId() {
        return entityId;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
