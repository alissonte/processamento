package com.desafio.associado.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Associado {

    @Id
    private String uuid;

    @Column(nullable = false, length = 14)
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;

    @Column(nullable = false)
    private String nome;

    public Associado(String numeroDocumento, TipoPessoa tipoPessoa, String nome) {
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.nome = nome;
    }

    public Associado() {}
}
