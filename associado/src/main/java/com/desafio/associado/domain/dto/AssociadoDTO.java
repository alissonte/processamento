package com.desafio.associado.domain.dto;

import com.desafio.associado.domain.model.TipoPessoa;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AssociadoDTO {

    private String numeroDocumento;

    private TipoPessoa tipoPessoa;

    private String nome;
}
