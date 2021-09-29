package com.tumotoya.concesionarios.domain.generics.values;

import co.com.sofka.domain.generic.Identity;
import com.tumotoya.concesionarios.domain.venta.values.ClienteID;

public class Placa extends Identity {
    public Placa(String id){
        super(id);
    }
    public static Placa of(String id){
        return  new Placa(id);
    }

}
