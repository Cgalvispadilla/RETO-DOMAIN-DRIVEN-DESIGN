package com.tumotoya.concesionarios.domain.concesionario.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Modelo implements ValueObject<String> {
    private final String value;

    public Modelo(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("El modelo de la moto no puede estar vacio");
        }
        if (this.value.length() <= 3) {
            throw new IllegalArgumentException("El modelo de la moto debe tener minimo tres letras");
        }
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modelo modelo = (Modelo) o;
        return Objects.equals(value, modelo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
