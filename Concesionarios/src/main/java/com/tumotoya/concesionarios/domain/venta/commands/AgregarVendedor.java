package com.tumotoya.concesionarios.domain.venta.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.VendedorID;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class AgregarVendedor extends Command {
    private final VentaID ventaID;
    private final VendedorID vendedorId;
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;

    public AgregarVendedor(VentaID ventaID, VendedorID vendedorId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion) {
        this.ventaID = ventaID;
        this.vendedorId = vendedorId;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
    }

    public VentaID getVentaID() {
        return ventaID;
    }

    public VendedorID getVendedorId() {
        return vendedorId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public NumeroCelular getNumeroCelular() {
        return numeroCelular;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
