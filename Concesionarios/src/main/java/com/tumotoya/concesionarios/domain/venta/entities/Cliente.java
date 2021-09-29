package com.tumotoya.concesionarios.domain.venta.entities;

import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;

public class Cliente extends Entity<ClienteID> {
    public Cliente(ClienteID entityId) {
        super(entityId);
    }
}
