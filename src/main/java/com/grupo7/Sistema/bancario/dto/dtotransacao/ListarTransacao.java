package com.grupo7.Sistema.bancario.dto.dtotransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.dto.dtocontabancaria.DadosDetalhamentoConta;
import com.grupo7.Sistema.bancario.entity.Transacao;
import com.grupo7.Sistema.bancario.enums.enumtransacao.Movimentacao;
import com.grupo7.Sistema.bancario.enums.enumtransacao.StatusTransacao;
import com.grupo7.Sistema.bancario.enums.enumtransacao.TipoTransacao;

public record ListarTransacao(

    Movimentacao movimentacao,
    DadosDetalhamentoConta idContaOrigem,
    DadosDetalhamentoConta idContaDestino,
    TipoTransacao tipo,
    StatusTransacao status,
    BigDecimal valor,
    LocalDateTime dataTransacao
) {
    public ListarTransacao(Transacao dados) {
        this(dados.getMovimentacao(), dados.getIdContaOrigem() != null ? new DadosDetalhamentoConta(dados.getIdContaOrigem()): null,
            new DadosDetalhamentoConta(dados.getIdContaDestino()),
            dados.getTipo(), dados.getStatus(), dados.getValor(), dados.getDataTransacao());
    }
}
