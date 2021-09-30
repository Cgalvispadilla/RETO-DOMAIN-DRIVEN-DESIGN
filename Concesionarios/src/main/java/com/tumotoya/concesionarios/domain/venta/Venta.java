package com.tumotoya.concesionarios.domain.venta;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.generics.values.*;
import com.tumotoya.concesionarios.domain.venta.entities.Cliente;
import com.tumotoya.concesionarios.domain.venta.entities.Detalle;
import com.tumotoya.concesionarios.domain.venta.entities.Vendedor;
import com.tumotoya.concesionarios.domain.venta.events.*;
import com.tumotoya.concesionarios.domain.venta.values.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Venta extends AggregateEvent<VentaID> {
    protected Fecha fecha;
    protected MetodoDePago metodoDePago;
    protected CostoTotal costoTotal;
    protected Set<Vendedor> vendedores;
    protected Cliente cliente;
    protected Set<Detalle> detalles;

    public Venta(VentaID entityId, Fecha fecha,MetodoDePago metodoDePago) {
        super(entityId);
        Objects.requireNonNull(fecha);
        Objects.requireNonNull(metodoDePago);
        appendChange(new VentaCreada(fecha, metodoDePago)).apply();
    }
    private Venta(VentaID  entityID){
        super(entityID);
        subscribe(new VentaChange(this));
    }
    public static Venta from(VentaID entityID, List<DomainEvent>events){
        Venta venta= new Venta(entityID);
        events.forEach(venta::applyEvent);
        return venta;
    }
    public void agregarCliente(ClienteID clienteId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Email email){
        Objects.requireNonNull(clienteId);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(numeroCelular);
        Objects.requireNonNull(direccion);
        Objects.requireNonNull(email);
        appendChange(new ClienteAgregado( clienteId,nombre,numeroCelular, direccion,email)).apply();
    }
    public void agregarVendedor(VendedorID vendedorId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion){
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(numeroCelular);
        Objects.requireNonNull(direccion);
      appendChange(new VendedorAgregado(vendedorId,nombre,numeroCelular, direccion)).apply();
    }
    public void actualizarVendedor(VendedorID entityId, Nombre nombre, NumeroCelular numeroCelular, Direccion direccion){
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(numeroCelular);
        Objects.requireNonNull(direccion);
        appendChange(new VendedorActualizado(entityId,nombre,numeroCelular, direccion)).apply();
    }
    public void ActualizarCliente( Nombre nombre, NumeroCelular numeroCelular, Direccion direccion, Email email){
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(numeroCelular);
        Objects.requireNonNull(direccion);
        Objects.requireNonNull(email);
       appendChange(new ClienteActualizado(nombre,numeroCelular, direccion,email)).apply();
    }
    public void agregarDetalle(DetalleID entityId, Placa placa, CantidadProducto cantidadProducto){
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(placa);
        Objects.requireNonNull(cantidadProducto);
       appendChange(new DetalleAgregado(entityId, placa, cantidadProducto)).apply();
    }
    public void actualizarCantidadProductoDetalle(DetalleID entityId, CantidadProducto cantidadProducto){
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(cantidadProducto);
       appendChange(new CantidadProductoDetalleActualizado(entityId, cantidadProducto));
    }

    public Fecha fecha() {
        return fecha;
    }

    public MetodoDePago metodoDePago() {
        return metodoDePago;
    }

    public CostoTotal costoTotal() {
        return costoTotal;
    }

    public Set<Vendedor> vendedores() {
        return vendedores;
    }

    public Cliente cliente() {
        return cliente;
    }

    public Set<Detalle> detalles() {
        return detalles;
    }

    public Optional<Detalle> obtenerDetallePorId(DetalleID detalleID) {
        return detalles().stream()
                .filter(detalle -> detalle.identity().equals(detalleID))
                .findFirst();
    }
    public Optional<Vendedor> obtenerVendedorPorId(VendedorID vendedorID) {
        return vendedores().stream()
                .filter(vendedor -> vendedor.identity().equals(vendedorID))
                .findFirst();
    }
}
