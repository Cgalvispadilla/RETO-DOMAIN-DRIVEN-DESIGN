package com.tumotoya.concesionarios.domain.venta.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class CantidadProducto implements ValueObject<Integer> {
    private final Integer value;

    public CantidadProducto(Integer value) {
        this.value = Objects.requireNonNull(value);
        if(this.value <=0){
            throw new IllegalArgumentException("La cantidad de producto no puede ser menor a cero");
        }
    }

    @Override
    public Integer value() {
        return value;
    }
}
