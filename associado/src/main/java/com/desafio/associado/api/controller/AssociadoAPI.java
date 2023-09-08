package com.desafio.associado.api.controller;

import com.desafio.associado.domain.dto.AssociadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(description = "Associados", name = "CRUD de Associados")
public interface AssociadoAPI {

    @Operation(description = "Adicionar um novo associado")
    @ApiResponse(responseCode = "201", description = "Associado criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    @PostMapping
    void inserir(@RequestBody @Valid AssociadoDTO associadoDTO);

    @Operation(description = "Atualizar um novo associado")
    @ApiResponse(responseCode = "201", description = "Associado atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @CrossOrigin
    @PutMapping("/{associadoId}")
    void atualizar(@PathVariable(name = "associadoId") String uuid, @RequestBody @Valid AssociadoDTO associadoDTO);

    @Operation(description = "Recuperar um associado por uuid")
    @ApiResponse(responseCode = "200", description = "Associado recuperado com sucesso")
    @ApiResponse(responseCode = "404", description = "Content not found")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @CrossOrigin
    @GetMapping("/{associadoId}")
    AssociadoDTO buscarPor(@PathVariable(name = "associadoId") String associadoId);

    @Operation(description = "Deletar um associado por uuid")
    @ApiResponse(responseCode = "204", description = "Associado deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{associadoId}")
    void deletar(@PathVariable(name = "associadoId") String associadoId);
}
