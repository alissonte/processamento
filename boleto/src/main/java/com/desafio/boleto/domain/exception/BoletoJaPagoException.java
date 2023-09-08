package com.desafio.boleto.domain.exception;

public class BoletoJaPagoException extends NegocioException {

    public BoletoJaPagoException(String mensagem) {
        super(mensagem);
    }
}
