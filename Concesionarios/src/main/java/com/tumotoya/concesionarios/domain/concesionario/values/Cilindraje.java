package com.tumotoya.concesionarios.domain.concesionario.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Cilindraje implements ValueObject<String> {
    private final String value;

    public Cilindraje(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("El cilindraje no puede estar vacio");
        }
        if (this.value.length() <= 2) {
            throw new IllegalArgumentException("el cilindraje debe tener minimo dos caracteres");
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
        Cilindraje that = (Cilindraje) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
