package com.tumotoya.concesionarios.domain.venta.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class ActualizarCliente extends Command {

    private final VentaID ventaID;
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;
    private final Email email;

    public ActualizarCliente(VentaID ventaID,  Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Email email) {
        this.ventaID = ventaID;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
        this.email = email;
    }

    public VentaID getVentaID() {
        return ventaID;
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

    public Email getEmail() {
        return email;
    }
}
