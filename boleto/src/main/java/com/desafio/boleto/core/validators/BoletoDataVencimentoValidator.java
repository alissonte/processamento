package com.desafio.boleto.core.validators;

import com.desafio.boleto.domain.exception.BoletoDataVencimentoException;

import java.time.LocalDate;

public class BoletoDataVencimentoValidator implements LocalDateValidator {

    @Override
    public void validate(LocalDate dataVencimento) {
        if (dataVencimento.isBefore(LocalDate.now())) {
            throw new BoletoDataVencimentoException("A data de vencimento do boleto não pode ser anterior à data atual!");
        }
    }
}
