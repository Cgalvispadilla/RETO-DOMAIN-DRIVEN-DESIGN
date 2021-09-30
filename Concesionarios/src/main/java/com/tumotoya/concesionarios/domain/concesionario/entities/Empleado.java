package com.tumotoya.concesionarios.domain.concesionario.entities;

import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.concesionario.values.EmpleadoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Rol;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;

public class Empleado extends Entity<EmpleadoID> {
    private EmpleadoID empleadoID;
    private Nombre nombre;
    private NumeroCelular numeroCelular;
    private Direccion direccion;
    private Rol rol;
    public Empleado(EmpleadoID entityId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Rol rol) {
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

    public void actualizarRol(Rol rol) {
        this.rol = rol;
    }

    public EmpleadoID empleadoID() {
        return empleadoID;
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

    public Rol rol() {
        return rol;
    }
}
