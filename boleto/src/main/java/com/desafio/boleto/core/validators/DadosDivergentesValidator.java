package com.desafio.boleto.core.validators;

import com.desafio.boleto.domain.dto.BoletoDTO;
import com.desafio.boleto.domain.exception.BoletoDePagamentoInvalidoException;
import com.desafio.boleto.domain.exception.BoletoNaoEncontradoException;
import com.desafio.boleto.domain.mapper.BoletoMapper;
import com.desafio.boleto.domain.model.Boleto;
import com.desafio.boleto.domain.repository.BoletoRepository;
import lombok.RequiredArgsConstructor;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;

@RequiredArgsConstructor
public class DadosDivergentesValidator implements Validator<BoletoDTO> {

    private final BoletoRepository boletoRepository;

    private final BoletoMapper boletoMapper;

    @Override
    public void validate(BoletoDTO boletoDTO) {
        Boleto boletoCandidato = boletoMapper.convertToEntity(boletoDTO);
        Boleto boletoExistente = boletoRepository.findById(boletoDTO.getUuid())
                .orElseThrow(() -> new BoletoNaoEncontradoException(String.format("Boleto de identificador %s não encontrado.", boletoDTO.getUuid())));
        boolean saoIguais = reflectionEquals(boletoCandidato, boletoExistente);

        if (!saoIguais) {
            throw new BoletoDePagamentoInvalidoException
                    (String.format("Boleto de identificador %s é invalido ao que esta cadastrado em nossa base de daddos", boletoDTO.getUuid()));

        }
    }
}
