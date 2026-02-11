package com.grupo7.Sistema.bancario.dto.dtocontabancaria;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.dto.dtocliente.DadosCliente;
import com.grupo7.Sistema.bancario.entity.ContaBancaria;
import com.grupo7.Sistema.bancario.enums.enumcontabancaria.StatusConta;
import com.grupo7.Sistema.bancario.enums.enumcontabancaria.TipoConta;

public record DadosDetalhamentoConta (
    
    long id,
    String numeroConta,
    String agencia,
    BigDecimal saldo,
    TipoConta tipoConta,
    StatusConta status,
    LocalDateTime dataCriacao,
    String apelido,
    DadosCliente titular
    
) {
    public DadosDetalhamentoConta(ContaBancaria conta) {
        this(conta.getId(), conta.getNumeroConta(), conta.getAgencia(),
         conta.getSaldo(), conta.getTipoConta(), conta.getStatus(), conta.getDataCriacao(),
        conta.getApelido(), new DadosCliente(conta.getTitular()));
    }
}