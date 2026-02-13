package com.grupo7.Sistema.bancario.dto.dtotransacao;

import java.math.BigDecimal;

import com.grupo7.Sistema.bancario.enums.enumtransferencia.TipoTransferencia;


public  record DadosFazerTransferencia(
    long idContaOrigem,
    long idContaDestino,
    TipoTransferencia tipo,
    BigDecimal valor

) {
    
}
