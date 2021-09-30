package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.venta.values.CostoTotal;

public class CostoTotalCalculado extends DomainEvent {
    private final CostoTotal costoTotal;

    public CostoTotalCalculado(CostoTotal costoTotal) {
        super("concesionarios.concesionario.costototal");
        this.costoTotal = costoTotal;
    }

    public CostoTotal getCostoTotal() {
        return costoTotal;
    }
}
