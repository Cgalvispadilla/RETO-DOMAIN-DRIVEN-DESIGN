package com.tumotoya.concesionarios.domain.concesionario.values;

import co.com.sofka.domain.generic.ValueObject;

import java.awt.*;
import java.util.Objects;

public class Marca implements ValueObject<String> {
    private final String value;

    public Marca(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("La marca no puede estar vacio");
        }
        if (this.value.length() <= 3) {
            throw new IllegalArgumentException("La marca debe tener minimo tres letras");
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
        Marca marca = (Marca) o;
        return Objects.equals(value, marca.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
