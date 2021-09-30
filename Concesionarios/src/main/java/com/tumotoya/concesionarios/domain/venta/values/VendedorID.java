package com.tumotoya.concesionarios.domain.venta.values;

import co.com.sofka.domain.generic.Identity;

public class VendedorID extends Identity {
    public VendedorID() {
    }

    public VendedorID(String id) {
        super(id);
    }

    public static VendedorID of(String id) {
        return new VendedorID(id);
    }
}
