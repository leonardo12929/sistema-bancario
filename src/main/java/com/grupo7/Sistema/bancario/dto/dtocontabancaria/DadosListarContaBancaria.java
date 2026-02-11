package com.grupo7.Sistema.bancario.dto.dtocontabancaria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.entity.Cliente;
import com.grupo7.Sistema.bancario.entity.ContaBancaria;
import com.grupo7.Sistema.bancario.enums.enumcontabancaria.StatusConta;
import com.grupo7.Sistema.bancario.enums.enumcontabancaria.TipoConta;

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
