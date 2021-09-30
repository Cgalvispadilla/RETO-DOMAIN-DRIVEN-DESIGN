package com.tumotoya.concesionarios.domain.concesionario;

import co.com.sofka.domain.generic.EventChange;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;

import java.util.HashSet;

public class ConcesionarioChange extends EventChange {
    public ConcesionarioChange(Concesionario concesionario) {

        apply((ConcesionarioCreado event)->{
            concesionario.nombre= event.getNombre();
            concesionario.empleados= new HashSet<>();
            concesionario.motos= new HashSet<>();
            concesionario.ventas= new HashSet<>();
        });
    }
}
