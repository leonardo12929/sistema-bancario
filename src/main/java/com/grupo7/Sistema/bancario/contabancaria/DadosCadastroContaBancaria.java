package com.grupo7.Sistema.bancario.contabancaria;


import jakarta.validation.constraints.NotBlank;
public record DadosCadastroContaBancaria(
    TipoConta tipoConta,
    @NotBlank
    String titular
) {
} 
    

