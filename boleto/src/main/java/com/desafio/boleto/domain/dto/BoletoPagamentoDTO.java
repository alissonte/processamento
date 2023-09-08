package com.desafio.boleto.domain.dto;

import com.desafio.boleto.domain.model.SituacaoBoleto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoletoPagamentoDTO extends BoletoDTO {
    private SituacaoBoleto situacaoBoleto;
}
