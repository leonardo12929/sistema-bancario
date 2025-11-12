package com.grupo7.Sistema.bancario.contabancaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarContaBancaria(
    @NotNull
    long id,    
    @NotBlank
    String titular) {
    
}
