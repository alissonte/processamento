package com.desafio.boleto.api.controller;

import com.desafio.boleto.domain.dto.BoletoDTO;
import com.desafio.boleto.domain.service.BoletoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boletos")
public class BoletoController implements BoletoApi {

    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @Override
    public void inserir(BoletoDTO boletoDTO) {
        boletoService.insere(boletoDTO);
    }

    @Override
    public BoletoDTO buscarPorAssociadoId(String associadoId) {
        return boletoService.buscarBoletoPorUuidDoAssociado(associadoId);
    }

    @Override
    public void atualizarPagamentoDeBoleto(BoletoDTO boletoPagamentoDTO) {
        boletoService.atualizarPagamento(boletoPagamentoDTO);
    }
}
