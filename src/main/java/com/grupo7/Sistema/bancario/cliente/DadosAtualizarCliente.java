package com.grupo7.Sistema.bancario.cliente;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCliente(
    @NotNull
    long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    LocalDate dataNascimento,
    String endereco
) {
    
}
