package com.desafio.associado.domain.service;

import com.desafio.associado.domain.model.Associado;
import com.desafio.associado.domain.model.TipoPessoa;

import java.util.UUID;

public class AssociadoBuilder {
    private String numeroDocumento;
    private TipoPessoa tipoPessoa;
    private String nome;
    private String uuid;

    public AssociadoBuilder numeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public AssociadoBuilder tipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
        return this;
    }

    public AssociadoBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public AssociadoBuilder uuid() {
        this.uuid = UUID.randomUUID().toString();
        return this;
    }

    public Associado build() {
        return new Associado(numeroDocumento, tipoPessoa, nome);
    }
}