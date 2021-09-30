package com.tumotoya.concesionarios.domain.concesionario.entities;

import co.com.sofka.domain.generic.Entity;
import com.tumotoya.concesionarios.domain.concesionario.values.Cilindraje;
import com.tumotoya.concesionarios.domain.concesionario.values.Marca;
import com.tumotoya.concesionarios.domain.concesionario.values.Modelo;
import com.tumotoya.concesionarios.domain.concesionario.values.Valor;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.Placa;

public class Moto extends Entity<Placa> {
    private Placa placa;
    private Nombre nombre;
    private Marca marca;
    private Modelo modelo;
    private Cilindraje cilindraje;
    private Valor valor;

    public Moto(Placa entityId, Nombre nombre, Marca marca, Modelo modelo, Cilindraje cilindraje, Valor valor) {
        super(entityId);
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindraje = cilindraje;
        this.valor = valor;
    }

    public void actualizarNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public void actualizarMarca(Marca marca) {
        this.marca = marca;
    }

    public void actualizarModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void actualizarCilindraje(Cilindraje cilindraje) {
        this.cilindraje = cilindraje;
    }

    public void actualizarValor(Valor valor) {
        this.valor = valor;
    }

    public Placa placa() {
        return placa;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Marca marca() {
        return marca;
    }

    public Modelo modelo() {
        return modelo;
    }

    public Cilindraje cilindraje() {
        return cilindraje;
    }

    public Valor valor() {
        return valor;
    }
}
