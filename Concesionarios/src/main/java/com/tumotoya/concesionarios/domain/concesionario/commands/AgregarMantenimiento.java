package com.tumotoya.concesionarios.domain.concesionario.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.concesionario.values.MantenimientoID;
import com.tumotoya.concesionarios.domain.concesionario.values.Valor;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;

public class AgregarMantenimiento extends Command {
    private final ConcesionarioID concesionarioID;
    private final MantenimientoID mantenimientoID;
    private final Nombre nombre;
    private final Valor valor;

    public AgregarMantenimiento(ConcesionarioID concesionarioID, MantenimientoID mantenimientoID, Nombre nombre, Valor valor) {
        this.concesionarioID = concesionarioID;
        this.mantenimientoID = mantenimientoID;
        this.nombre = nombre;
        this.valor = valor;
    }

    public ConcesionarioID getConcesionarioID() {
        return concesionarioID;
    }

    public MantenimientoID getMantenimientoID() {
        return mantenimientoID;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Valor getValor() {
        return valor;
    }
}
