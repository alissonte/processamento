package com.desafio.boleto.core.validators;

import com.desafio.boleto.domain.dto.BoletoDTO;
import com.desafio.boleto.domain.exception.BoletoJaPagoException;
import com.desafio.boleto.domain.exception.BoletoNaoEncontradoException;
import com.desafio.boleto.domain.model.Boleto;
import com.desafio.boleto.domain.repository.BoletoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoletoPagoValidator implements Validator<BoletoDTO> {

    private final BoletoRepository boletoRepository;

    @Override
    public void validate(BoletoDTO boletoDTO) {
        Boleto boleto = boletoRepository.findById(boletoDTO.getUuid())
                .orElseThrow(() -> new BoletoNaoEncontradoException(String.format("Boleto de identificador %s não encontrado.", boletoDTO.getUuid())));

        if(boleto.jaPago()) {
            throw new BoletoJaPagoException(String.format("Boleto com identidicador %s já se encontra pago em nossa base de dados.", boleto.getUuid()));
        }
    }
}
