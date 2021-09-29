package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Email;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class ClienteAgregado extends DomainEvent {

    private final ClienteID clienteID;
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;
    private final Email email;

    public ClienteAgregado( ClienteID clienteId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Email email) {
        super("concesionarios.concesionario.clienteagregado");
        this.clienteID = clienteId;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
        this.email = email;
    }

    public ClienteID getClienteID() {
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
