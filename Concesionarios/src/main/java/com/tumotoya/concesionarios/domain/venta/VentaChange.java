package com.tumotoya.concesionarios.domain.venta;

import co.com.sofka.domain.generic.EventChange;
import com.tumotoya.concesionarios.domain.venta.entities.Cliente;
import com.tumotoya.concesionarios.domain.venta.entities.Vendedor;
import com.tumotoya.concesionarios.domain.venta.events.ClienteAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VendedorActualizado;
import com.tumotoya.concesionarios.domain.venta.events.VendedorAgregado;
import com.tumotoya.concesionarios.domain.venta.events.VentaCreada;

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

    }
}
