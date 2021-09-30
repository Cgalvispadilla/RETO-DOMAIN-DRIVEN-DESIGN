package com.tumotoya.concesionarios.domain.concesionario.values;

import co.com.sofka.domain.generic.Identity;
import com.tumotoya.concesionarios.domain.concesionario.entities.Mantenimiento;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;

public class MantenimientoID extends Identity {
    public MantenimientoID() {
    }

    public MantenimientoID(String id) {
        super(id);
    }

    public static MantenimientoID of(String id) {
        return new MantenimientoID(id);
    }
}
