package com.tumotoya.concesionarios.domain.concesionario;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.entities.Empleado;
import com.tumotoya.concesionarios.domain.concesionario.entities.Mantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.entities.Moto;
import com.tumotoya.concesionarios.domain.concesionario.events.*;
import com.tumotoya.concesionarios.domain.concesionario.values.*;
import com.tumotoya.concesionarios.domain.generics.values.Direccion;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.NumeroCelular;
import com.tumotoya.concesionarios.domain.generics.values.Placa;
import com.tumotoya.concesionarios.domain.venta.Venta;
import com.tumotoya.concesionarios.domain.venta.VentaChange;
import com.tumotoya.concesionarios.domain.venta.entities.Vendedor;
import com.tumotoya.concesionarios.domain.venta.values.VendedorID;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public void agregarEmpleado(EmpleadoID entityId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Rol rol) {
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(numeroCelular);
        Objects.requireNonNull(direccion);
        appendChange(new EmpleadoAgregado(entityId, nombre, numeroCelular, direccion, rol)).apply();
    }

    public void agregarMantenimiento(MantenimientoID mantenimientoID, Nombre nombre, Valor valor){
        Objects.requireNonNull(mantenimientoID);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(valor);
        appendChange(new MantenimientoAgregado(mantenimientoID, nombre, valor)).apply();
    }
    public void agregarMoto(Placa placa, Nombre nombre, Marca marca, Modelo modelo, Cilindraje cilindraje, Valor valor){
        Objects.requireNonNull(placa);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(marca);
        Objects.requireNonNull(modelo);
        Objects.requireNonNull(cilindraje);
        Objects.requireNonNull(valor);
        appendChange(new MotoAgregada(placa, nombre, marca, modelo, cilindraje, valor)).apply();
    }

    public void agregarVenta(VentaID ventaID){
        Objects.requireNonNull(ventaID);
        appendChange(new VentaAgregada(ventaID)).apply();
    }
    public void actualizarEmpleado(EmpleadoID entityId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Rol rol){
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(numeroCelular);
        Objects.requireNonNull(direccion);
        appendChange(new EmpleadoActualizado(entityId, nombre, numeroCelular, direccion, rol)).apply();
    }
    public void actualizarMoto(Placa placa, Nombre nombre, Marca marca, Modelo modelo, Cilindraje cilindraje, Valor valor){
        Objects.requireNonNull(placa);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(marca);
        Objects.requireNonNull(modelo);
        Objects.requireNonNull(cilindraje);
        Objects.requireNonNull(valor);
        appendChange(new MotoActualizada(placa, nombre, marca, modelo, cilindraje, valor)).apply();
    }
    public void actualizarValorMantenimiento(MantenimientoID mantenimientoID, Valor valor){
        Objects.requireNonNull(mantenimientoID);
        Objects.requireNonNull(valor);
        appendChange(new ValorMantenimientoActualizado(mantenimientoID,valor)).apply();
    }
    public Optional<Empleado> obtenerEmpleadoPorId(EmpleadoID empleadoID) {
        return empleados().stream()
                .filter(empleado -> empleado.identity().equals(empleadoID))
                .findFirst();
    }
    public Optional<Moto> obtenerMotoPorPlaca(Placa placa) {
        return motos().stream()
                .filter(empleado -> empleado.identity().equals(placa))
                .findFirst();
    }
    public Optional<Mantenimiento> obtenerMatenimientoPorId(MantenimientoID mantenimientoID) {
        return mantenimientos().stream()
                .filter(empleado -> empleado.identity().equals(mantenimientoID))
                .findFirst();
    }
    public Nombre nombre() {
        return nombre;
    }

    public Set<Empleado> empleados() {
        return empleados;
    }

    public Set<Moto> motos() {
        return motos;
    }

    public Set<Mantenimiento> mantenimientos() {
        return mantenimientos;
    }

    public Set<VentaID> ventas() {
        return ventas;
    }
}
