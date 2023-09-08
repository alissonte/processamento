package com.desafio.associado.api.controller;

import com.desafio.associado.domain.dto.AssociadoDTO;
import com.desafio.associado.domain.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associados")
@RequiredArgsConstructor
@Slf4j
public class AssociadoController implements AssociadoAPI {

    private final AssociadoService associadoService;


    @Override
    public void inserir(AssociadoDTO associadoDTO) {
        log.info("Adicionando um novo associado {}", associadoDTO.getNome());
        associadoService.insere(associadoDTO);
    }

    @Override
    public void atualizar(String uuid, AssociadoDTO associadoDTO) {
        log.info("Adicionando um novo associado {}", associadoDTO.getNome());
        associadoService.update(associadoDTO, uuid);
    }

    @Override
    public AssociadoDTO buscarPor(String associadoId) {
        return associadoService.buscarAssociadoPor(associadoId);
    }

    @Override
    public void deletar(String associadoId) {
        associadoService.excluir(associadoId);
    }
}
