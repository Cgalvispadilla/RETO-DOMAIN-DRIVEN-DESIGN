package com.tumotoya.concesionarios.domain.concesionario.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.values.MantenimientoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Valor;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;

public class MantenimientoAgregado extends DomainEvent {
    private final MantenimientoID mantenimientoID;
    private final Nombre nombre;
    private final Valor valor;

    public MantenimientoAgregado(MantenimientoID mantenimientoID, Nombre nombre, Valor valor) {
        super("concesionarios.concesionario.mantenimientoagregado");
        this.mantenimientoID = mantenimientoID;
        this.nombre = nombre;
        this.valor = valor;
    }

    public MantenimientoID getMantenimientoID() {
        return mantenimientoID;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Valor getValor() {
        return valor;
    }
}
