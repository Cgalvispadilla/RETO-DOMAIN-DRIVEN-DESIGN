package com.tumotoya.concesionarios.domain.venta.values;

import co.com.sofka.domain.generic.Identity;

public class DetalleID extends Identity {
    public DetalleID(String id){
        super(id);
    }
    public static DetalleID of(String id){
        return  new DetalleID(id);
    }
}
