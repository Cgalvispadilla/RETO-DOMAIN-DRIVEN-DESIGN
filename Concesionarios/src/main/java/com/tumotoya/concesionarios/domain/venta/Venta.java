package com.tumotoya.concesionarios.domain.venta;

import co.com.sofka.domain.generic.AggregateEvent;
import com.tumotoya.concesionarios.domain.venta.entities.Cliente;
import com.tumotoya.concesionarios.domain.venta.entities.Detalle;
import com.tumotoya.concesionarios.domain.venta.entities.Vendedor;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;
import com.tumotoya.concesionarios.domain.venta.values.CostoTotal;
import com.tumotoya.concesionarios.domain.venta.values.Fecha;
import com.tumotoya.concesionarios.domain.venta.values.MetodoDePago;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

import java.util.Set;

public class Venta extends AggregateEvent<VentaID> {
    protected Fecha fecha;
    protected MetodoDePago metodoDePago;
    protected CostoTotal costoTotal;
    protected Set<Vendedor> vendedores;
    protected Cliente cliente;
    protected Set<Detalle> detalles;

    public Venta(VentaID entityId, Fecha fecha,MetodoDePago metodoDePago) {
        super(entityId);
        appendChange(new VentaCreada(fecha, metodoDePago)).apply();
    }


}
