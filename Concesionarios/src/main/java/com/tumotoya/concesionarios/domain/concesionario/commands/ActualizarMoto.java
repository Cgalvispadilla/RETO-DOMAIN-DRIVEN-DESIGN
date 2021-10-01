package com.tumotoya.concesionarios.domain.concesionario.commands;

import co.com.sofka.domain.generic.Command;
import com.tumotoya.concesionarios.domain.concesionario.values.*;
import com.tumotoya.concesionarios.domain.generics.values.Nombre;
import com.tumotoya.concesionarios.domain.generics.values.Placa;

public class ActualizarMoto extends Command {
    private final ConcesionarioID concesionarioID;
    private final Placa placa;
    private final Nombre nombre;
    private final Marca marca;
    private final Modelo modelo;
    private final Cilindraje cilindraje;
    private final Valor valor;

    public ActualizarMoto(ConcesionarioID concesionarioID, Placa placa, Nombre nombre, Marca marca, Modelo modelo, Cilindraje cilindraje, Valor valor) {
        this.concesionarioID = concesionarioID;
        this.placa = placa;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindraje = cilindraje;
        this.valor = valor;
    }

    public ConcesionarioID getConcesionarioID() {
        return concesionarioID;
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
