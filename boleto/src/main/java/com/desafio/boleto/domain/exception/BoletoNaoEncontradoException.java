package com.desafio.boleto.domain.exception;

import java.io.Serial;

public class BoletoNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BoletoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
