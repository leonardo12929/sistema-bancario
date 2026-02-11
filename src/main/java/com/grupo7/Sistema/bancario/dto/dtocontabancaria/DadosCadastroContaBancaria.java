package com.grupo7.Sistema.bancario.dto.dtocontabancaria;


import com.grupo7.Sistema.bancario.enums.enumcontabancaria.TipoConta;

import jakarta.validation.constraints.NotBlank;
public record DadosCadastroContaBancaria(
    TipoConta tipoConta,
    @NotBlank
    String apelido,
    long titular
) {
} 
    

