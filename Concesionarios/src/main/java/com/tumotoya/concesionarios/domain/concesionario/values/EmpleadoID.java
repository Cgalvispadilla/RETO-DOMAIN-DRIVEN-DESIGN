package com.tumotoya.concesionarios.domain.concesionario.values;

import co.com.sofka.domain.generic.Identity;
import com.tumotoya.concesionarios.domain.concesionario.entities.Empleado;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;

public class EmpleadoID extends Identity {
    public EmpleadoID() {
    }

    public EmpleadoID(String id) {
        super(id);
    }

    public static EmpleadoID of(String id) {
        return new EmpleadoID(id);
    }
}
