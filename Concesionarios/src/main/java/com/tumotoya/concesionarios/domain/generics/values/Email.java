package com.tumotoya.concesionarios.domain.generics.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Email implements ValueObject<String> {
    private  final String value;

    public Email(String value) {
        this.value = Objects.requireNonNull(value);
        if(this.value.isBlank()){
            throw new IllegalArgumentException("El Email no puede estar vació");
        }
        if(this.value.length()<=5){
            throw new IllegalArgumentException("El Email debe tener minimo 5 caracteres");
        }
        if(!value.matches("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("El email no es valido");
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
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
