package com.grupo7.Sistema.bancario.service.servicetransacao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo7.Sistema.bancario.dto.dtotransacao.ListarTransacao;
import com.grupo7.Sistema.bancario.dto.dtotransacao.TransacaoOutros;
import com.grupo7.Sistema.bancario.dto.dtotransacao.DadosTransacao;
import com.grupo7.Sistema.bancario.entity.Transacao;
import com.grupo7.Sistema.bancario.repository.ContaBancariaRepository;
import com.grupo7.Sistema.bancario.repository.TransacaoRepository;

@Transactional
@Service
public class TransacaoService {

    private TransacaoRepository repository;
    private ContaBancariaRepository repositoryConta;
    
    public TransacaoService(TransacaoRepository repository, ContaBancariaRepository repositoryConta ) {
        this.repository = repository;
        this.repositoryConta = repositoryConta;
    }
    
    public void transferencia(DadosTransacao dados) {
        var contaOrigem = repositoryConta.getReferenceById(dados.idContaOrigem());
        var contaDestino = repositoryConta.getReferenceById(dados.idContaDestino());

        BigDecimal saldoContaOrigemSubtraida = contaOrigem.getSaldo().subtract(dados.valor());
        BigDecimal saldoContaDestinoSomada = contaDestino.getSaldo().add(dados.valor());

        if (dados.valor().compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException ("Valor Inválido");
        }
        
        if (saldoContaOrigemSubtraida.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Algo de errado");
        }
        var transacao = new Transacao();
        transacao.setTipo(dados.tipo());
        transacao.setValor(dados.valor());
        
        
        contaOrigem.setSaldo(saldoContaOrigemSubtraida);
        contaDestino.setSaldo(saldoContaDestinoSomada);

        transacao.setIdContaOrigem(contaOrigem);
        transacao.setIdContaDestino(contaDestino);

        
        repository.save(transacao);
    } 

    public Transacao saque(DadosTransacao dados) {

        var contaDestino = repositoryConta.getReferenceById(dados.idContaDestino());
        BigDecimal saldoContaDestinoSubtraida = contaDestino.getSaldo().subtract(dados.valor());

        if (saldoContaDestinoSubtraida.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        var transacao = new Transacao();
        transacao.setTipo(dados.tipo());
        transacao.setValor(dados.valor());

        contaDestino.setSaldo(saldoContaDestinoSubtraida);

        transacao.setIdContaDestino(contaDestino);
        transacao.setIdContaOrigem(null);
        repository.save(transacao);

        return transacao;
    }

    public List<ListarTransacao> exibirTransacao() {
        return repository.findAll().stream().map(ListarTransacao:: new).toList();
    }
}
