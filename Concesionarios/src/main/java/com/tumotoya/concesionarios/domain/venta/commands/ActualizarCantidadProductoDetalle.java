package com.tumotoya.concesionarios.domain.venta.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.venta.values.CantidadProducto;
import com.tumotoya.concesionarios.domain.venta.values.DetalleID;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class ActualizarCantidadProductoDetalle extends Command {
    private final VentaID ventaID;
    private final DetalleID entityId;
    private final CantidadProducto cantidadProducto;

    public ActualizarCantidadProductoDetalle(VentaID ventaID, DetalleID entityId, CantidadProducto cantidadProducto) {
        this.ventaID = ventaID;
        this.entityId = entityId;
        this.cantidadProducto = cantidadProducto;
    }

    public VentaID getVentaID() {
        return ventaID;
    }

    public DetalleID getEntityId() {
        return entityId;
    }

    public CantidadProducto getCantidadProducto() {
        return cantidadProducto;
    }
}
