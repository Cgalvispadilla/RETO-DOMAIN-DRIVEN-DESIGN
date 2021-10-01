package com.tumotoya.concesionarios.domain.concesionario.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.values.MantenimientoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Valor;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;

public class ValorMantenimientoActualizado extends DomainEvent {
    private final MantenimientoID mantenimientoID;
    private final Valor valor;

    public ValorMantenimientoActualizado(MantenimientoID mantenimientoID, Valor valor) {
        super("concesionarios.concesionario.mantenimientoactualizado");
        this.mantenimientoID = mantenimientoID;
        this.valor = valor;
    }

    public MantenimientoID getMantenimientoID() {
        return mantenimientoID;
    }

    public Valor getValor() {
        return valor;
    }
}
