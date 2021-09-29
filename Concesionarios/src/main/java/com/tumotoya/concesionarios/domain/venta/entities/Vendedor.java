package com.tumotoya.concesionarios.domain.venta.entities;

import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.VendedorID;

public class Vendedor extends Entity<VendedorID> {
    private VendedorID  vendedorID;
    private Nombre nombre;
    private NumeroCelular numeroCelular;
    private Direccion direccion;

    public Vendedor(VendedorID entityId,  Nombre nombre, NumeroCelular numeroCelular, Direccion direccion) {
        super(entityId);
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
    }

    public void actualizarNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public void actualizarNumeroCelular(NumeroCelular numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public void actualizarDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public VendedorID vendedorID() {
        return vendedorID;
    }

    public Nombre nombre() {
        return nombre;
    }

    public NumeroCelular numeroCelular() {
        return numeroCelular;
    }

    public Direccion direccion() {
        return direccion;
    }
}
