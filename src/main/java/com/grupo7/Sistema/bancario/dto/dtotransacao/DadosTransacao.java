package com.grupo7.Sistema.bancario.dto.dtotransacao;

import java.math.BigDecimal;

import com.grupo7.Sistema.bancario.enums.enumtransacao.TipoTransacao;


public  record DadosTransacao(
    long idContaOrigem,
    long idContaDestino,
    TipoTransacao tipo,
    BigDecimal valor

) {
    
}
