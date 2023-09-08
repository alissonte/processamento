package com.desafio.boleto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoBoleto {

    PAGO("Pago"),
    NAO_PAGO("Não pago");

    private final String descricao;
}
