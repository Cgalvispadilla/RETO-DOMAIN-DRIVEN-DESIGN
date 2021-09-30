package com.tumotoya.concesionarios.domain.venta.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.generics.values.Placa;
import com.tumotoya.concesionarios.domain.venta.values.CantidadProducto;
import com.tumotoya.concesionarios.domain.venta.values.DetalleID;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class AgregarDetalle extends Command {

    private final VentaID ventaID;
    private final DetalleID detalleID;
    private final Placa placa;
    private final CantidadProducto cantidadProducto;

    public AgregarDetalle(VentaID ventaID, DetalleID detalleID, Placa placa, CantidadProducto cantidadProducto) {
        this.ventaID = ventaID;
        this.detalleID = detalleID;
        this.placa = placa;
        this.cantidadProducto = cantidadProducto;
    }

    public VentaID getVentaID() {
        return ventaID;
    }

    public DetalleID getDetalleID() {
        return detalleID;
    }

    public Placa getPlaca() {
        return placa;
    }

    public CantidadProducto getCantidadProducto() {
        return cantidadProducto;
    }
}
