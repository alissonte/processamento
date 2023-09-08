package com.desafio.associado.domain.service;

import com.desafio.associado.core.util.DocumentoUtil;
import com.desafio.associado.core.validators.ValidadorDeCNPJ;
import com.desafio.associado.domain.exception.AssociadoNaoEncontradoException;
import com.desafio.associado.domain.exception.DocumentoInvalidoException;
import com.desafio.associado.domain.exception.EntidadeEmUsoException;
import com.desafio.associado.domain.model.Associado;
import com.desafio.associado.domain.model.TipoPessoa;
import com.desafio.associado.domain.repository.AssociadoRepository;
import com.desafio.associado.domain.dto.AssociadoDTO;
import com.desafio.associado.domain.mapper.AssociadoMapper;
import com.desafio.associado.core.validators.ValidadorDeCPF;
import com.desafio.associado.core.validators.ValidadorDeDocumento;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssociadoService {

    private static final String MSG_ASSOCIADO_NAO_ENCONTRADO
            = "Não existe um cadastro de associado com uuid %s";

    private static final String MSG_ASSOCIADO_EM_USO
            = "Associado de uuid %s não pode ser removido, pois está em uso";

    private final AssociadoRepository associadoRepository;

    private final AssociadoMapper associadoMapper;

    @Transactional
    public void insere(AssociadoDTO associadoDTO) {
        validaCpfCnpj(associadoDTO);
        Associado associado = associadoMapper.convertToEntity(associadoDTO);
        associadoRepository.save(associado);
    }

    @Transactional
    public void update(AssociadoDTO associadoDTO, String uuid) {
        validaCpfCnpj(associadoDTO);
        Associado associado = buscarAssociado(uuid);
        BeanUtils.copyProperties(associadoDTO, associado, "uuid");
        associadoRepository.save(associado);
    }

    public AssociadoDTO buscarAssociadoPor(String uuid) {
        return associadoMapper.convertToDTO(buscarAssociado(uuid));
    }

    public Page<AssociadoDTO> buscarTodosPaginados(Pageable pageable) {
        return associadoMapper.convertToPageDTO(associadoRepository.findAll(pageable));
    }

    @Transactional
    public void excluir(String uuid) {
        try {
            associadoRepository.deleteById(uuid);

        } catch (EmptyResultDataAccessException e) {
            throw new AssociadoNaoEncontradoException(uuid);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ASSOCIADO_EM_USO, uuid));
        }
    }

    public Associado buscarAssociado(String uuid) {
        return associadoRepository.findById(uuid).orElseThrow(() ->
                new AssociadoNaoEncontradoException(String.format(MSG_ASSOCIADO_NAO_ENCONTRADO, uuid)));
    }

    private void validaCpfCnpj(AssociadoDTO associadoDTO) {
        String documento = DocumentoUtil.somenteNumeros(associadoDTO.getNumeroDocumento());

        Map<TipoPessoa, ValidadorDeDocumento> validadores = new HashMap<>();
        validadores.put(TipoPessoa.PF, new ValidadorDeCPF());
        validadores.put(TipoPessoa.PJ, new ValidadorDeCNPJ());

        ValidadorDeDocumento validador = validadores.get(associadoDTO.getTipoPessoa());

        if (validador == null || !validador.validar(documento)) {
            throw new DocumentoInvalidoException("Documento não é um CPF ou CNPJ válido.");
        }
    }
}
