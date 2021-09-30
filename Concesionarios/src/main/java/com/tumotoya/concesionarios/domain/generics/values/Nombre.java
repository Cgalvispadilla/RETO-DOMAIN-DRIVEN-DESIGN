package com.tumotoya.concesionarios.domain.generics.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Nombre implements ValueObject<String> {
    private final String value;

    public Nombre(String value) {
        this.value = Objects.requireNonNull(value);
        if (this.value.isBlank()) {
            throw new IllegalArgumentException("El Nombre no puede estar vació");
        }
        if (this.value.length() <= 3) {
            throw new IllegalArgumentException("El nombre debe tener minimo tres letras");
        }
        if (this.value.length() >= 100) {
            throw new IllegalArgumentException("El nombre debe tener menos de 100 letras");
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
        Nombre nombre = (Nombre) o;
        return Objects.equals(value, nombre.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
