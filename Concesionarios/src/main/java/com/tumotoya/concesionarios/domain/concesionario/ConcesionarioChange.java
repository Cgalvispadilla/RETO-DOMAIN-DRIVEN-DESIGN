package com.tumotoya.concesionarios.domain.concesionario;

import co.com.sofka.domain.generic.EventChange;
import com.tumotoya.concesionarios.domain.concesionario.commands.AgregarMantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.entities.Empleado;
import com.tumotoya.concesionarios.domain.concesionario.entities.Mantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.events.EmpleadoAgregado;
import com.tumotoya.concesionarios.domain.concesionario.events.MantenimientoAgregado;
import com.tumotoya.concesionarios.domain.concesionario.values.EmpleadoID;

import java.util.HashSet;

public class ConcesionarioChange extends EventChange {
    public ConcesionarioChange(Concesionario concesionario) {

        apply((ConcesionarioCreado event) -> {
            concesionario.nombre = event.getNombre();
            concesionario.empleados = new HashSet<>();
            concesionario.motos = new HashSet<>();
            concesionario.mantenimientos= new HashSet<>();
            concesionario.ventas = new HashSet<>();
        });

        apply((EmpleadoAgregado event) -> {
            concesionario.empleados.add(new Empleado(
                    event.getEntityId(),
                    event.getNombre(),
                    event.getNumeroCelular(),
                    event.getDireccion(),
                    event.getRol()
            ));
        });

        apply((MantenimientoAgregado event)->{
            concesionario.mantenimientos.add(new Mantenimiento(
                    event.getMantenimientoID(),
                    event.getNombre(),
                    event.getValor()
            ));
        });
    }
}
