package com.tumotoya.concesionarios.domain.venta.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.venta.values.VendedorID;

public class VendedorActualizado extends DomainEvent {
    private final VendedorID entityId;
    private final Nombre nombre;
    private final NumeroCelular numeroCelular;
    private final Direccion direccion;

    public VendedorActualizado(VendedorID entityId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion) {
        super("concesionarios.concesionario.vendedoractualizado");
        this.entityId = entityId;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
    }

    public VendedorID getEntityId() {
        return entityId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public NumeroCelular getNumeroCelular() {
        return numeroCelular;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
