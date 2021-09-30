package com.tumotoya.concesionarios.domain.generics.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Direccion implements ValueObject<String> {
    private final String value;

    public Direccion(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isBlank()) {
            throw new IllegalArgumentException("La dirección no puede estar vació");
        }
        if (this.value.length() <= 5) {
            throw new IllegalArgumentException("La dirección debe tener minimo tres letras");
        }
        if (this.value.length() >= 100) {
            throw new IllegalArgumentException("La dirección debe tener menos de 100 letras");
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
        Direccion direccion = (Direccion) o;
        return Objects.equals(value, direccion.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
