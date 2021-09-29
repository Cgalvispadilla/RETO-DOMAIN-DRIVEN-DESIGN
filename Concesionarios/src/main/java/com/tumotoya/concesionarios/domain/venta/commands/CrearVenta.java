package com.tumotoya.concesionarios.domain.venta.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class CrearVenta extends Command {

    private final VentaID entityId;
    private final Fecha fecha;
    private final MetodoDePago metodoDePago;

    public CrearVenta(VentaID entityId, Fecha fecha, MetodoDePago metodoDePago) {
        this.entityId = entityId;
        this.fecha = fecha;
        this.metodoDePago = metodoDePago;
    }

    public VentaID getEntityId() {
        return entityId;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }
}
