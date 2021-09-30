package com.tumotoya.concesionarios.domain.concesionario.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.values.EmpleadoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Rol;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;

public class EmpleadoAgregado extends DomainEvent {
    private final EmpleadoID entityId;
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;
    private final Rol rol;

    public EmpleadoAgregado(EmpleadoID entityId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Rol rol) {
        super("concesionarios.concesionario.empleadoagregado");
        this.entityId = entityId;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
        this.rol = rol;
    }

    public EmpleadoID getEntityId() {
        return entityId;
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

    public Rol getRol() {
        return rol;
    }
}
