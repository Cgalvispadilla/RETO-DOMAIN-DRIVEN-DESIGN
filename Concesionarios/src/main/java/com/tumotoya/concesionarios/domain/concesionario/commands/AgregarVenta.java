package com.tumotoya.concesionarios.domain.concesionario.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class AgregarVenta extends Command {
    private final ConcesionarioID concesionarioID;
    private final VentaID ventaID;

    public AgregarVenta(ConcesionarioID concesionarioID, VentaID ventaID) {
        this.concesionarioID = concesionarioID;
        this.ventaID = ventaID;
    }

    public ConcesionarioID getConcesionarioID() {
        return concesionarioID;
    }

    public VentaID getVentaID() {
        return ventaID;
    }
}
