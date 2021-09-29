package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.VendedorID;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class VendedorAgregado extends DomainEvent {
    private final VendedorID vendedorId;
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;

    public VendedorAgregado(VendedorID vendedorId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion) {
        super("concesionarios.concesionario.vendedoragregado");
        this.vendedorId = vendedorId;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
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
