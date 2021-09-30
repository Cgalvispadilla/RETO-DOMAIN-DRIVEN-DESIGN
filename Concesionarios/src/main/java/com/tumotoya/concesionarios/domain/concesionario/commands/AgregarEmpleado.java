package com.tumotoya.concesionarios.domain.concesionario.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.concesionario.values.EmpleadoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Rol;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;

public class AgregarEmpleado extends Command {
    private final ConcesionarioID concesionarioID;
    private final EmpleadoID empleadoID;
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;
    private final Rol rol;

    public AgregarEmpleado(ConcesionarioID concesionarioID, EmpleadoID empleadoID, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Rol rol) {
        this.concesionarioID = concesionarioID;
        this.empleadoID = empleadoID;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
        this.rol = rol;
    }

    public ConcesionarioID getConcesionarioID() {
        return concesionarioID;
    }

    public EmpleadoID getEmpleadoID() {
        return empleadoID;
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
