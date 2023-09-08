package com.desafio.boleto.domain.service;

import com.desafio.boleto.api.client.AssociadoApiClient;
import com.desafio.boleto.domain.dto.BoletoDTO;
import com.desafio.boleto.domain.exception.AssociadoNaoEncontradoException;
import com.desafio.boleto.domain.exception.BoletoDePagamentoInvalidoException;
import com.desafio.boleto.domain.exception.BoletoNaoEncontradoException;
import com.desafio.boleto.domain.exception.BoletoDataVencimentoException;
import com.desafio.boleto.domain.mapper.BoletoMapper;
import com.desafio.boleto.domain.model.Boleto;
import com.desafio.boleto.domain.repository.BoletoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoletoServiceTest {

    @InjectMocks
    private BoletoService boletoService;

    @Mock
    private BoletoRepository boletoRepository;

    @Mock
    private AssociadoApiClient associadoApiClient;

    @Mock
    private BoletoMapper boletoMapper;

    @Test
    void insere_ShouldSaveBoleto_WhenAssociadoExists() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now());

        Boleto boleto = new Boleto();

        when(associadoApiClient.associadoExists(anyString())).thenReturn(true);
        when(boletoMapper.convertToEntity(boletoDTO)).thenReturn(boleto);

        boletoService.insere(boletoDTO);

        verify(boletoRepository).save(boleto);
    }

    @Test
    void insere_ShouldThrowException_WhenAssociadoDoesNotExist() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now());

        when(associadoApiClient.associadoExists(anyString())).thenReturn(false);

        assertThrows(AssociadoNaoEncontradoException.class, () -> boletoService.insere(boletoDTO));
    }

    @Test
    void insere_ShouldThrowException_WhenErrorOccursOnSave() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now());

        Boleto boleto = new Boleto();

        when(associadoApiClient.associadoExists(anyString())).thenReturn(true);
        when(boletoMapper.convertToEntity(boletoDTO)).thenReturn(boleto);
        doThrow(new RuntimeException()).when(boletoRepository).save(boleto);

        assertThrows(RuntimeException.class, () -> boletoService.insere(boletoDTO));
    }

    @Test
    void insere_ShouldThrowException_WhenVencimentoIsBeforeCurrentDate() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now().minusDays(1));

        when(associadoApiClient.associadoExists(boletoDTO.getUuidAssociado())).thenReturn(true);

        assertThrows(BoletoDataVencimentoException.class, () -> boletoService.insere(boletoDTO));
    }

    @Test
    void buscarBoletoPorUUIDDoAssociado_Success() {
        String uuid = "someUuid";
        Boleto boleto = new Boleto();
        BoletoDTO boletoDTO = new BoletoDTO();

        when(boletoRepository.findBoletoByUuidAssociado(uuid)).thenReturn(Optional.of(boleto));
        when(boletoMapper.convertToDTO(boleto)).thenReturn(boletoDTO);
        when(associadoApiClient.associadoExists(uuid)).thenReturn(true);

        BoletoDTO result = boletoService.buscarBoletoPorUuidDoAssociado(uuid);

        assertThat(boletoDTO).isEqualTo(result);
    }

    @Test
    void buscarBoletoPorUUIDDoAssociado_BoletoNotFound() {
        String uuid = UUID.randomUUID().toString();

        when(boletoRepository.findBoletoByUuidAssociado(uuid)).thenReturn(Optional.empty());
        when(associadoApiClient.associadoExists(uuid)).thenReturn(true);

        assertThrows(BoletoNaoEncontradoException.class, () -> boletoService.buscarBoletoPorUuidDoAssociado(uuid));
    }

    @Test
    void buscarBoletoPorUUIDDoAssociado_InvalidUUID() {
        String invalidUuid = UUID.randomUUID().toString();

        when(associadoApiClient.associadoExists(invalidUuid)).thenReturn(false);

        assertThrows(AssociadoNaoEncontradoException.class, () -> boletoService.buscarBoletoPorUuidDoAssociado(invalidUuid));
    }

    @Test
    void atualizarPagamento_ValidInput_SavesBoleto() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now());

        Boleto boleto = new Boleto();

        when(boletoRepository.findById(boletoDTO.getUuid())).thenReturn(Optional.of(boleto));
        when(associadoApiClient.associadoExists(boletoDTO.getUuidAssociado())).thenReturn(true);
        when(boletoMapper.convertToEntity(boletoDTO)).thenReturn(boleto);

        boletoService.atualizarPagamento(boletoDTO);

        verify(boletoRepository).save(boleto);
    }

    @Test
    void atualizarPagamento_InValidInput_ThenThrowsDadosDivergentes() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now());

        Boleto boleto = new Boleto();
        boleto.setUuid(UUID.randomUUID().toString());
        boleto.setDataVencimento(LocalDate.now().minusDays(1));

        when(boletoRepository.findById(boletoDTO.getUuid())).thenReturn(Optional.of(boleto));
        when(associadoApiClient.associadoExists(boletoDTO.getUuidAssociado())).thenReturn(true);

        assertThrows(BoletoDePagamentoInvalidoException.class, () -> boletoService.atualizarPagamento(boletoDTO));
    }

    @Test
    void atualizarPagamento_InValidAssocioadoIdInput_ThenThrowsAssociadoNotFound() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now());

        Boleto boleto = new Boleto();
        boleto.setUuid(UUID.randomUUID().toString());
        boleto.setDataVencimento(LocalDate.now().minusDays(1));

        when(associadoApiClient.associadoExists(boletoDTO.getUuidAssociado())).thenReturn(false);

        assertThrows(AssociadoNaoEncontradoException.class, () -> boletoService.atualizarPagamento(boletoDTO));
    }

    @Test
    void atualizarPagamento_InDataVencimentoInvalidaInput_ThenThrowsBoletoDataVencimentoInvalida() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUuidAssociado(UUID.randomUUID().toString());
        boletoDTO.setDataVencimento(LocalDate.now().minusDays(10));

        Boleto boleto = new Boleto();
        boleto.setUuid(UUID.randomUUID().toString());
        boleto.setDataVencimento(LocalDate.now().minusDays(1));

        when(associadoApiClient.associadoExists(boletoDTO.getUuidAssociado())).thenReturn(true);

        assertThrows(BoletoDataVencimentoException.class, () -> boletoService.atualizarPagamento(boletoDTO));
    }
}