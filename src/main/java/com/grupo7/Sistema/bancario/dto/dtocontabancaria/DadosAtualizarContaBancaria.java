package com.grupo7.Sistema.bancario.dto.dtocontabancaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarContaBancaria(
    @NotNull
    long id,    
    @NotBlank
    String apelido) {
    
}
