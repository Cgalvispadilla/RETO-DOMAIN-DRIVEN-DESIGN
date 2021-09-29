package com.tumotoya.concesionarios.domain.venta.entities;


import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.venta.values.DetalleID;

public class Detalle extends Entity<DetalleID> {
    public Detalle(DetalleID entityId) {
        super(entityId);
    }
}
