package com.tumotoya.concesionarios.domain.venta.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.venta.values.CostoTotal;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

public class CalcularCostoTotal extends Command {
    private final VentaID ventaID;
    private final CostoTotal costoTotal;

    public CalcularCostoTotal(VentaID ventaID, CostoTotal costoTotal) {
        this.ventaID = ventaID;
        this.costoTotal = costoTotal;
    }

    public VentaID getVentaID() {
        return ventaID;
    }

    public CostoTotal getCostoTotal() {
        return costoTotal;
    }
}
