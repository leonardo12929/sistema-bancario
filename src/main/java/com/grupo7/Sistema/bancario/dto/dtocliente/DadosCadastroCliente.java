package com.grupo7.Sistema.bancario.dto.dtocliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
    @NotBlank
    String nome,
    @NotBlank
    String cpf,
    @NotBlank
    String email,
    @NotBlank
    String telefone,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate dataNascimento,
    @NotBlank
    String endereco
) {
    
}
