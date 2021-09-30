package com.tumotoya.concesionarios.domain.concesionario.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Rol implements ValueObject<String> {
    private final String value;

    public Rol(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("El Rol del empleado no puede estar vacio");
        }
        if (this.value.length() <= 2) {
            throw new IllegalArgumentException("El Rol del empleado debe tener minimo dos Caracteres");
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
        Rol rol = (Rol) o;
        return Objects.equals(value, rol.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
