package com.desafio.associado.core.validators;

import br.com.caelum.stella.validation.CPFValidator;


public class ValidadorDeCPF implements ValidadorDeDocumento {

    @Override
    public boolean validar(String documento) {
        try {
            new CPFValidator().assertValid(documento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
