package com.tumotoya.concesionarios.domain.venta;

import co.com.sofka.domain.generic.EventChange;
import com.tumotoya.concesionarios.domain.venta.entities.Cliente;
import com.tumotoya.concesionarios.domain.venta.entities.Detalle;
import com.tumotoya.concesionarios.domain.venta.entities.Vendedor;
import com.tumotoya.concesionarios.domain.venta.events.*;

import java.util.HashSet;

public class VentaChange extends EventChange {
    public VentaChange(Venta venta) {
        apply((VentaCreada event) -> {
            venta.fecha = event.getFecha();
            venta.metodoDePago = event.getMetodoDePago();
            venta.vendedores = new HashSet<>();
            venta.detalles = new HashSet<>();
        });
        apply((ClienteAgregado event) -> {
            venta.cliente = new Cliente(event.getClienteID(), event.getNombre(), event.getNumeroCelular(), event.getDireccion(), event.getEmail());
        });

        apply((VendedorAgregado event) -> {
            venta.vendedores.add(
                    new Vendedor(event.getVendedorId(),
                            event.getNombre(),
                            event.getNumeroCelular(),
                            event.getDireccion())
            );
        });
        apply((VendedorActualizado event) -> {
            Vendedor vendedor = venta.obtenerVendedorPorId(event.getEntityId())
                    .orElseThrow(() -> new IllegalArgumentException("No existe un vendedor con ID " + event.getEntityId()));
            vendedor.actualizarNombre(event.getNombre());
            vendedor.actualizarNumeroCelular(event.getNumeroCelular());
            vendedor.actualizarDireccion(event.getDireccion());
        });
        apply((ClienteActualizado event) -> {
            venta.cliente.actualizarNombre(event.getNombre());
            venta.cliente.actualizarDireccion(event.getDireccion());
            venta.cliente.actualizarNumeroCelular(event.getNumeroCelular());
            venta.cliente.actualizarEmail(event.getEmail());
        });

        apply((DetalleAgregado event)->{
            venta.detalles.add(
                    new Detalle(event.getEntityId(),
                            event.getPlaca(),
                            event.getCantidadProducto()
                    )
            );
        });

    }
}
