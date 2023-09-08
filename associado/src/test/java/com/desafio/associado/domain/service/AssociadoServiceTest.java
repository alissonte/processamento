package com.desafio.associado.domain.service;

import com.desafio.associado.domain.dto.AssociadoDTO;
import com.desafio.associado.domain.exception.DocumentoInvalidoException;
import com.desafio.associado.domain.mapper.AssociadoMapper;
import com.desafio.associado.domain.model.Associado;
import com.desafio.associado.domain.model.TipoPessoa;
import com.desafio.associado.domain.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    @Mock
    private AssociadoMapper associadoMapper;

    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private AssociadoService associadoService;

    private String nome;
    private String numDocumento;

    @BeforeEach
    public void setUp() {
        nome = "Associado Teste";
        numDocumento = "32835666079";
    }

    @Test
    void shouldInsertAssociado() {
        // Arrange
        AssociadoDTO associadoDTO = AssociadoDTO.builder()
                .nome(nome)
                .numeroDocumento(numDocumento)
                .tipoPessoa(TipoPessoa.PF)
                .build();

        Associado associado = new AssociadoBuilder()
                .numeroDocumento(numDocumento)
                .tipoPessoa(TipoPessoa.PF)
                .nome(nome)
                .build();

        when(associadoMapper.convertToEntity(associadoDTO)).thenReturn(associado);

        //Act
        associadoService.insere(associadoDTO);

        //Assert
        verify(associadoRepository, times(1)).save(associado);
    }

    @Test
    void shouldThrowsExceptionWhenCpfInvalid() {
        // Arrange
        AssociadoDTO associadoDTO = AssociadoDTO.builder()
                .nome(nome)
                .numeroDocumento("87776767")
                .tipoPessoa(TipoPessoa.PF)
                .build();

        try {
            //Act
            associadoService.insere(associadoDTO);
            fail();
        }catch(DocumentoInvalidoException e) {
            //Assert
            verify(associadoRepository, never()).save(any(Associado.class));
            assertThat(e.getMessage()).isEqualTo("Documento não é um CPF ou CNPJ válido.");
        }
    }

    @Test
    void shouldThrowsExceptionWhenCnpjInvalid() {
        // Arrange
        AssociadoDTO associadoDTO = AssociadoDTO.builder()
                .nome(nome)
                .numeroDocumento("897349872374-0001/14")
                .tipoPessoa(TipoPessoa.PF)
                .build();

        try {
            //Act
            associadoService.insere(associadoDTO);
            fail();
        }catch(DocumentoInvalidoException e) {
            //Assert
            verify(associadoRepository, never()).save(any(Associado.class));
            assertThat(e.getMessage()).isEqualTo("Documento não é um CPF ou CNPJ válido.");
        }
    }
}