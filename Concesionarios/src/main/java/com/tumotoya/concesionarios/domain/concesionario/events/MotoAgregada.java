package com.tumotoya.concesionarios.domain.concesionario.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.tumotoya.concesionarios.domain.concesionario.values.Cilindraje;
import com.tumotoya.concesionarios.domain.concesionario.values.Marca;
import com.tumotoya.concesionarios.domain.concesionario.values.Modelo;
import com.tumotoya.concesionarios.domain.concesionario.values.Valor;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.Placa;

public class MotoAgregada extends DomainEvent {
    private final Placa placa;
    private final Nombre nombre;
    private final Marca marca;
    private final Modelo modelo;
    private final Cilindraje cilindraje;
    private final Valor valor;

    public MotoAgregada(Placa placa, Nombre nombre, Marca marca, Modelo modelo, Cilindraje cilindraje, Valor valor) {
        super("concesionarios.concesionario.motoagregada");
        this.placa = placa;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindraje = cilindraje;
        this.valor = valor;
    }

    public Placa getPlaca() {
        return placa;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Marca getMarca() {
        return marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public Cilindraje getCilindraje() {
        return cilindraje;
    }

    public Valor getValor() {
        return valor;
    }
}
