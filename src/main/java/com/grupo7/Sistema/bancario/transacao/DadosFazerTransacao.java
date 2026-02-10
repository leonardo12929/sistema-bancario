package com.grupo7.Sistema.bancario.transacao;

import java.math.BigDecimal;


public  record DadosFazerTransacao(
    long idContaOrigem,
    long idContaDestino,
    Tipo tipo,
    BigDecimal valor

) {
    
}
