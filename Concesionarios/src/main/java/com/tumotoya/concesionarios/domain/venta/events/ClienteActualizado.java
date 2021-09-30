package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;

public class ClienteActualizado extends DomainEvent {
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;
    private final Email email;

    public ClienteActualizado( Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Email email) {
        super("concesionarios.concesionario.clienteactualizado");
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
        this.email = email;
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
