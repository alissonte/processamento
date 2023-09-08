package com.desafio.boleto.domain.service;

import com.desafio.boleto.api.client.AssociadoApiClient;
import com.desafio.boleto.core.validators.*;
import com.desafio.boleto.domain.dto.BoletoDTO;
import com.desafio.boleto.domain.exception.BoletoNaoEncontradoException;
import com.desafio.boleto.domain.mapper.BoletoMapper;
import com.desafio.boleto.domain.model.Boleto;
import com.desafio.boleto.domain.repository.BoletoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BoletoService {

    private final BoletoRepository boletoRepository;

    private final BoletoMapper boletoMapper;

    private final AssociadoApiClient associadoApiClient;

    public BoletoService(BoletoRepository boletoRepository, BoletoMapper boletoMapper, AssociadoApiClient associadoApiClient) {
        this.boletoRepository = boletoRepository;
        this.boletoMapper = boletoMapper;
        this.associadoApiClient = associadoApiClient;
    }

    @Transactional
    public void insere(BoletoDTO boletoDTO) {
        validacaoBasicaAoSalvarBoleto(boletoDTO);
        Boleto boleto = boletoMapper.convertToEntity(boletoDTO);
        boletoRepository.save(boleto);
    }

    @Transactional
    public void atualizarPagamento(BoletoDTO boletoPagamentoDTO) {
        validacaoBasicaAoSalvarBoleto(boletoPagamentoDTO);
        validacaoDadosDivergentes(boletoPagamentoDTO);
        validacaoBoletoPago(boletoPagamentoDTO);
        Boleto boletoPago = boletoMapper.convertToEntity(boletoPagamentoDTO);
        boletoRepository.save(boletoPago);
    }

    private void validacaoBoletoPago(BoletoDTO boletoPagamentoDTO) {
        BoletoPagoValidator boletoPagoValidator = new BoletoPagoValidator(boletoRepository);
        boletoPagoValidator.validate(boletoPagamentoDTO);
    }

    private void validacaoDadosDivergentes(BoletoDTO boletoPagamentoDTO) {
        DadosDivergentesValidator dadosDivergentesValidator = new DadosDivergentesValidator(boletoRepository, boletoMapper);
        dadosDivergentesValidator.validate(boletoPagamentoDTO);
    }

    public BoletoDTO buscarBoletoPorUuidDoAssociado(String uuidAssociado) {
        validacaoUuidAssociado(uuidAssociado);

        Boleto boleto = boletoRepository.findBoletoByUuidAssociado(uuidAssociado)
                .orElseThrow(() -> new BoletoNaoEncontradoException(String.format("Boleto do associado %s não encontrado", uuidAssociado)));

        return boletoMapper.convertToDTO(boleto);
    }

    private void validacaoBasicaAoSalvarBoleto(BoletoDTO boletoDTO) {
        validacaoUuidAssociado(boletoDTO.getUuidAssociado());
        validacaoVencimentoDoBoleto(boletoDTO);
    }

    private static void validacaoVencimentoDoBoleto(BoletoDTO boletoDTO) {
        LocalDateValidator localDateValidator = new BoletoDataVencimentoValidator();
        localDateValidator.validate(boletoDTO.getDataVencimento());
    }

    private void validacaoUuidAssociado(String uuidAssociado) {
        StringValidator uuidValidator = new AssociadoExistenteValidator(associadoApiClient);
        uuidValidator.validate(uuidAssociado);
    }
}
