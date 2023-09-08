package com.desafio.associado.core.validators;

import br.com.caelum.stella.validation.CNPJValidator;

public class ValidadorDeCNPJ implements ValidadorDeDocumento {

    @Override
    public boolean validar(String documento) {
        try {
            new CNPJValidator().assertValid(documento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
