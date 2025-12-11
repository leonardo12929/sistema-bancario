package com.grupo7.Sistema.bancario.contabancaria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.cliente.Cliente;

import jakarta.validation.constraints.NotBlank;
public record DadosListarContaBancaria(
    @NotBlank
    long id,
    String apelido,
    String numeroConta,
    BigDecimal saldo,
    Cliente titular,
    String agencia,
    TipoConta tipoConta,
    StatusConta status,
    LocalDateTime datacriacao
) {

    public DadosListarContaBancaria(ContaBancaria dados) {
        this(dados.getId(), dados.getApelido(), dados.getNumeroConta(),
        dados.getSaldo(), dados.getTitular(), dados.getAgencia(),
        dados.getTipoConta(), dados.getStatus(), dados.getDataCriacao());
    }
    
}
