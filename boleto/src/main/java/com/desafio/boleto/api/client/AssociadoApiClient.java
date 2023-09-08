package com.desafio.boleto.api.client;

import com.desafio.boleto.domain.exception.AssociadoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class AssociadoApiClient {

    private final String BASE_URL = "http://localhost:8080";

    private final RestTemplate restTemplate;

    public boolean associadoExists(String associadoId) {
        String endpoint = BASE_URL + "/associados/" + associadoId;

        try {
            restTemplate.getForEntity(endpoint, Void.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new AssociadoNaoEncontradoException(String.format("Não encontrado associado com o id %s", associadoId));
        }
    }
}
