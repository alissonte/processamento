package com.desafio.boleto.core.validators;

import com.desafio.boleto.api.client.AssociadoApiClient;
import com.desafio.boleto.domain.exception.AssociadoNaoEncontradoException;

public class AssociadoExistenteValidator implements StringValidator {

    private final AssociadoApiClient associadoApiClient;

    public AssociadoExistenteValidator(AssociadoApiClient associadoApiClient) {
        this.associadoApiClient = associadoApiClient;
    }

    @Override
    public void validate(String uuidAssociado) {
        if (!associadoApiClient.associadoExists(uuidAssociado)) {
            throw new AssociadoNaoEncontradoException(String.format("Associado com id %s não encontrado", uuidAssociado));
        }
    }
}
