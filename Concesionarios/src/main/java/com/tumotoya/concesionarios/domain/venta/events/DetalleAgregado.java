package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Placa;
import com.tumotoya.concesionarios.domain.venta.values.CantidadProducto;
import com.tumotoya.concesionarios.domain.venta.values.DetalleID;

public class DetalleAgregado extends DomainEvent {
    private final DetalleID entityId;
    private final Placa placa;
    private final CantidadProducto cantidadProducto;

    public DetalleAgregado(DetalleID entityId, Placa placa, CantidadProducto cantidadProducto) {
        super("concesionarios.concesionario.detalleagregado");
        this.entityId = entityId;
        this.placa = placa;
        this.cantidadProducto = cantidadProducto;
    }

    public DetalleID getEntityId() {
        return entityId;
    }

    public Placa getPlaca() {
        return placa;
    }

    public CantidadProducto getCantidadProducto() {
        return cantidadProducto;
    }
}
