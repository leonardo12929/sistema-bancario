package com.grupo7.Sistema.bancario.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo7.Sistema.bancario.dto.dtotransacao.DadosFazerTransacao;
import com.grupo7.Sistema.bancario.entity.Transacao;
import com.grupo7.Sistema.bancario.repository.ContaBancariaRepository;
import com.grupo7.Sistema.bancario.repository.TransacaoRepository;

@Service
public class TransacaoService {

    private TransacaoRepository repository;
    private ContaBancariaRepository repositoryConta;
    
    public TransacaoService(TransacaoRepository repository, ContaBancariaRepository repositoryConta ) {
        this.repository = repository;
        this.repositoryConta = repositoryConta;
    }
    
    @Transactional
    public void fazerTransacao(DadosFazerTransacao dados) {
        var contaOrigem = repositoryConta.getReferenceById(dados.idContaOrigem());
        var contaDestino = repositoryConta.getReferenceById(dados.idContaDestino());

        BigDecimal valorContaOrigemSubtraida = contaOrigem.getSaldo().subtract(dados.valor());
        BigDecimal valorContaDestinoSomada = contaDestino.getSaldo().add(dados.valor());

        if (dados.valor().compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException ("Valor Inválido");
        }
        
        if (valorContaOrigemSubtraida.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Algo de errado");
        }
        var transacao = new Transacao();
        transacao.setTipo(dados.tipo());
        transacao.setValor(dados.valor());
        
        
        contaOrigem.setSaldo(valorContaOrigemSubtraida);
        contaDestino.setSaldo(valorContaDestinoSomada);

        transacao.setIdContaOrigem(contaOrigem);
        transacao.setIdContaDestino(contaDestino);

        
        repository.save(transacao);
    } 
}
