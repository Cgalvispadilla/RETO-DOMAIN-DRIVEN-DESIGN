package com.tumotoya.concesionarios.domain.concesionario;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.entities.Empleado;
import com.tumotoya.concesionarios.domain.concesionario.entities.Mantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.entities.Moto;
import com.tumotoya.concesionarios.domain.concesionario.events.ConcesionarioCreado;
import com.tumotoya.concesionarios.domain.concesionario.values.ConcesionarioID;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.VentaChange;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Concesionario extends AggregateEvent<ConcesionarioID> {
    protected Nombre nombre;
    protected Set<Empleado> empleados;
    protected Set<Moto> motos;
    protected Set<Mantenimiento> mantenimientos;
    protected Set<VentaID> ventas;

    public Concesionario(ConcesionarioID entityId, Nombre nombre) {
        super(entityId);
        Objects.requireNonNull(nombre);
        appendChange(new ConcesionarioCreado(nombre)).apply();
    }
    private Concesionario(ConcesionarioID entityID) {
        super(entityID);
        subscribe(new ConcesionarioChange(this));
    }

    public static Concesionario from(ConcesionarioID entityID, List<DomainEvent> events) {
        Concesionario concesionario = new Concesionario(entityID);
        events.forEach(concesionario::applyEvent);
        return concesionario;
    }

}