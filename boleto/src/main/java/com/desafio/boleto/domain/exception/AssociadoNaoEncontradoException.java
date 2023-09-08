package com.desafio.boleto.domain.exception;

import java.io.Serial;

public class AssociadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AssociadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
