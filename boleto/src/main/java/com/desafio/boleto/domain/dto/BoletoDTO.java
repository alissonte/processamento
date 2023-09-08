package com.desafio.boleto.domain.dto;

import com.desafio.boleto.api.controller.OnUpdate;
import com.desafio.boleto.domain.model.SituacaoBoleto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BoletoDTO {

    private String uuid;

    @NotBlank(groups = OnUpdate.class, message = "UUID não pode ser nulo ou vazio")
    private BigDecimal valor;

    private LocalDate dataVencimento;

    @NotBlank(message = "UUID do associado não pode ser nulo ou vazio")
    private String uuidAssociado;

    @NotBlank(message = "Documento do pagador não pode ser nulo ou vazio")
    private String documentoPagador;

    private String nomePagador;

    private String nomeFantasiaPagador;

    private SituacaoBoleto situacao;
}
