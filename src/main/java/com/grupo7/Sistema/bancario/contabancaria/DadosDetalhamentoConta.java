package com.grupo7.Sistema.bancario.contabancaria;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.cliente.DadosCliente;

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