package com.desafio.boleto.core.validators;

public interface Validator<T> {

    void validate(T object);

}
