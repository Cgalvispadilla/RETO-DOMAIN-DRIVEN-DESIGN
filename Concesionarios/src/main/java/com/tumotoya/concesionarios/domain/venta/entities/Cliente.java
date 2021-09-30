package com.tumotoya.concesionarios.domain.venta.entities;

import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;


public class Cliente extends Entity<ClienteID> {
    private ClienteID clienteID;
    private Nombre nombre;
    private NumeroCelular numeroCelular;
    private Direccion direccion;
    private Email email;

    public Cliente(ClienteID entityId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Email email) {
        super(entityId);
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
        this.email = email;
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

    public void actualizarEmail(Email email) {
        this.email = email;
    }


    public ClienteID clienteID() {
        return clienteID;
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
