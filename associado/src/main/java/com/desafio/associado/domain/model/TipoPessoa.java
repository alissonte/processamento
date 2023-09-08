package com.desafio.associado.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoPessoa {
    PF("Pessoa Física"),
    PJ("Pessoa Jurídica");

    private final String descricao;
}
