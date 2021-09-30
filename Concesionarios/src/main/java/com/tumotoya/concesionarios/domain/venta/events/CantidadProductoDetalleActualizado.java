package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.venta.values.CantidadProducto;
import com.tumotoya.concesionarios.domain.venta.values.DetalleID;

public class CantidadProductoDetalleActualizado extends DomainEvent {
    private final DetalleID entityId;
    private final CantidadProducto cantidadProducto;

    public CantidadProductoDetalleActualizado(DetalleID entityId, CantidadProducto cantidadProducto) {
        super("concesionarios.concesionario.cantidadProductoDetalleActualizado");
        this.entityId = entityId;
        this.cantidadProducto = cantidadProducto;
    }

    public DetalleID getEntityId() {
        return entityId;
    }

    public CantidadProducto getCantidadProducto() {
        return cantidadProducto;
    }
}
