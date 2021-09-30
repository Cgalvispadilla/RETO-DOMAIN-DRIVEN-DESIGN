package com.tumotoya.concesionarios.domain.venta.values;

import co.com.sofka.domain.generic.Identity;

public class VentaID extends Identity {
    public VentaID(){}
    public VentaID(String id){
        super(id);
    }
    public static VentaID of(String id){
        return  new VentaID(id);
    }
}
