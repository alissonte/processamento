package com.desafio.boleto.api.controller;

import com.desafio.boleto.domain.dto.BoletoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(description = "Boletos", name = "Gerenciamento de Boletos")
public interface BoletoApi {

    @Operation(description = "Adicionar um novo boleto a um associado")
    @ApiResponse(responseCode = "201", description = "Associado criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    @PostMapping
    void inserir(@Validated(OnCreate.class) @RequestBody BoletoDTO boletoDTO);

    @Operation(description = "Recuperar um boleto a partir do uuid do associado")
    @ApiResponse(responseCode = "200", description = "Boleto recuperado com sucesso")
    @ApiResponse(responseCode = "404", description = "Content not found")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @CrossOrigin
    @GetMapping("/associados/{associadoId}")
    BoletoDTO buscarPorAssociadoId(@PathVariable(name = "associadoId") String associadoId);

    @Operation(description = "Recuperar um boleto a partir do uuid do associado")
    @ApiResponse(responseCode = "201", description = "Boleto recuperado com sucesso")
    @ApiResponse(responseCode = "404", description = "Content not found")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @CrossOrigin
    @PutMapping
    void atualizarPagamentoDeBoleto(@Validated(OnUpdate.class) @RequestBody BoletoDTO boletoPagamentoDTO);

}
