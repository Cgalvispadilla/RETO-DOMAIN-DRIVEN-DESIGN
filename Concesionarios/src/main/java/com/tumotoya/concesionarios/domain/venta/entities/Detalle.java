package com.tumotoya.concesionarios.domain.venta.entities;


import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.generics.values.Placa;
import com.tumotoya.concesionarios.domain.venta.values.CantidadProducto;
import com.tumotoya.concesionarios.domain.venta.values.DetalleID;

public class Detalle extends Entity<DetalleID> {
    private DetalleID detalleID;
    private Placa placa;
    private CantidadProducto cantidadProducto;

    public Detalle(DetalleID entityId, Placa placa, CantidadProducto cantidadProducto) {
        super(entityId);
        this.placa = placa;
        this.cantidadProducto = cantidadProducto;
    }

    public void actualizarCantidadProducto(CantidadProducto cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public DetalleID detalleID() {
        return detalleID;
    }

    public Placa placa() {
        return placa;
    }

    public CantidadProducto cantidadProducto() {
        return cantidadProducto;
    }
}
