package com.desafio.associado.domain.exception;

import java.io.Serial;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

    @Serial
    private static final long serialVersionUID = 1L;

    EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
