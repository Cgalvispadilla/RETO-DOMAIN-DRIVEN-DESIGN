package com.tumotoya.concesionarios.domain.venta.entities;


import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.venta.values.CantidadProducto;
import com.tumotoya.concesionarios.domain.venta.values.DetalleID;
import com.tumotoya.concesionarios.domain.venta.values.ProductoID;

public class Detalle extends Entity<DetalleID> {
    private  DetalleID detalleID;
    private ProductoID productoID;
    private CantidadProducto cantidadProducto;

    public Detalle(DetalleID entityId, DetalleID detalleID, ProductoID productoID, CantidadProducto cantidadProducto) {
        super(entityId);
        this.detalleID = detalleID;
        this.productoID = productoID;
        this.cantidadProducto = cantidadProducto;
    }

    public void actualizarCantidadProducto(CantidadProducto cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public ProductoID getProductoID() {
        return productoID;
    }

    public CantidadProducto getCantidadProducto() {
        return cantidadProducto;
    }
}
