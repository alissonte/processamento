package com.desafio.boleto.domain.exception;

public class BoletoDePagamentoInvalidoException extends NegocioException {

    public BoletoDePagamentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
