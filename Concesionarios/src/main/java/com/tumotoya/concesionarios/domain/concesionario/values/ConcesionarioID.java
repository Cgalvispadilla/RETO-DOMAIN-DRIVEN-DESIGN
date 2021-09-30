package com.tumotoya.concesionarios.domain.concesionario.values;

import co.com.sofka.domain.generic.Identity;

public class ConcesionarioID extends Identity {
    public ConcesionarioID() {
    }

    public ConcesionarioID(String id) {
        super(id);
    }

    public static ConcesionarioID of(String id) {
        return new ConcesionarioID(id);
    }
}
