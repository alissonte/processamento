package com.desafio.boleto.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(
        name = "boleto",
        uniqueConstraints = @UniqueConstraint(columnNames = {"uuid", "uuidAssociado"})
)
public class Boleto {

    @Id
    private String uuid;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Column(nullable = false)
    private String uuidAssociado;

    @Column(length = 14)
    private String documentoPagador;

    @Column(length = 50)
    private String nomePagador;

    @Column(length = 50)
    private String nomeFantasiaPagador;

    @Enumerated(EnumType.STRING)
    private SituacaoBoleto situacao = SituacaoBoleto.NAO_PAGO;

    public boolean jaPago() {
        return this.situacao.equals(SituacaoBoleto.PAGO);
    }
}
