package com.tumotoya.concesionarios.domain.concesionario;

import co.com.sofka.domain.generic.EventChange;
import com.tumotoya.concesionarios.domain.concesionario.entities.Empleado;
import com.tumotoya.concesionarios.domain.concesionario.entities.Mantenimiento;
import com.tumotoya.concesionarios.domain.concesionario.entities.Moto;
import com.tumotoya.concesionarios.domain.concesionario.events.*;
import com.tumotoya.concesionarios.domain.venta.values.VentaID;

import java.util.HashSet;

public class ConcesionarioChange extends EventChange {
    public ConcesionarioChange(Concesionario concesionario) {

        apply((ConcesionarioCreado event) -> {
            concesionario.nombre = event.getNombre();
            concesionario.empleados = new HashSet<>();
            concesionario.motos = new HashSet<>();
            concesionario.mantenimientos = new HashSet<>();
            concesionario.ventas = new HashSet<>();
        });

        apply((EmpleadoAgregado event) -> {
            concesionario.empleados.add(new Empleado(
                    event.getEntityId(),
                    event.getNombre(),
                    event.getNumeroCelular(),
                    event.getDireccion(),
                    event.getRol()
            ));
        });

        apply((MantenimientoAgregado event) -> {
            concesionario.mantenimientos.add(new Mantenimiento(
                    event.getMantenimientoID(),
                    event.getNombre(),
                    event.getValor()
            ));
        });

        apply((MotoAgregada event) -> {
            concesionario.motos.add(new Moto(
                    event.getPlaca(),
                    event.getNombre(),
                    event.getMarca(),
                    event.getModelo(),
                    event.getCilindraje(),
                    event.getValor()
            ));
        });

        apply((VentaAgregada event) -> {
            concesionario.ventas.add(new VentaID(event.getVentaID().value()));
        });

        apply((EmpleadoActualizado event) -> {
            var empleado = concesionario.obtenerEmpleadoPorId(event.getEntityId())
                    .orElseThrow(() -> new IllegalArgumentException("No existe ningun empleado con el id " + event.getEntityId()));
            empleado.actualizarNombre(event.getNombre());
            empleado.actualizarDireccion(event.getDireccion());
            empleado.actualizarNumeroCelular(event.getNumeroCelular());
            empleado.actualizarRol(event.getRol());
        });

        apply((MotoActualizada event) -> {
            var moto = concesionario.obtenerMotoPorPlaca(event.getPlaca())
                    .orElseThrow(() -> new IllegalArgumentException("No existe ninguna moto con la placa " + event.getPlaca()));
            moto.actualizarNombre(event.getNombre());
            moto.actualizarCilindraje(event.getCilindraje());
            moto.actualizarMarca(event.getMarca());
            moto.actualizarModelo(event.getModelo());
            moto.actualizarValor(event.getValor());
        });

        apply((ValorMantenimientoActualizado event) -> {
            var mantenimiento = concesionario.obtenerMatenimientoPorId(event.getMantenimientoID())
                    .orElseThrow(() -> new IllegalArgumentException("No existe ningún mantenimiento con el id " + event.getMantenimientoID()));
            mantenimiento.actualizarValor(event.getValor());
        });

    }
}
