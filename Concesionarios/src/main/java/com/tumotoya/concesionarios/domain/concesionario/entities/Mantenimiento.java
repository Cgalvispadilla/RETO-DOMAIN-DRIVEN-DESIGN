package com.tumotoya.concesionarios.domain.concesionario.entities;

import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.concesionario.values.MantenimientoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Valor;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;

public class Mantenimiento extends Entity<MantenimientoID> {
    private MantenimientoID mantenimientoID;
    private Nombre nombre;
    private Valor valor;

    public Mantenimiento(MantenimientoID entityId, Nombre nombre, Valor valor) {
        super(entityId);
        this.nombre = nombre;
        this.valor = valor;
    }

    public void actualizarNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public void actualizarValor(Valor valor) {
        this.valor = valor;
    }

    public MantenimientoID mantenimientoID() {
        return mantenimientoID;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Valor valor() {
        return valor;
    }
}
