package com.grupo7.Sistema.bancario.service.servicetransacao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo7.Sistema.bancario.dto.dtotransacao.ListarTransacao;
import com.grupo7.Sistema.bancario.dto.dtotransacao.DadosTransacao;
import com.grupo7.Sistema.bancario.entity.Transacao;
import com.grupo7.Sistema.bancario.enums.enumtransacao.Movimentacao;
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
    

    //||| TIPOS DE MOVIMENTAÇÕES |||

    // TRANSFERENCIA
    public Transacao transferencia(DadosTransacao dados) {
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
        transacao.setMovimentacao(Movimentacao.TRNSFERENCIA);
        
        
        contaOrigem.setSaldo(saldoContaOrigemSubtraida);
        contaDestino.setSaldo(saldoContaDestinoSomada);

        transacao.setIdContaOrigem(contaOrigem);
        transacao.setIdContaDestino(contaDestino);

        
        repository.save(transacao);

        return transacao;
    } 

    // SAQUE
    public Transacao saque(DadosTransacao dados) {

        var contaDestino = repositoryConta.getReferenceById(dados.idContaDestino());
        BigDecimal saldoContaDestinoSubtraida = contaDestino.getSaldo().subtract(dados.valor());

        if (saldoContaDestinoSubtraida.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        var transacao = new Transacao();
        transacao.setTipo(dados.tipo());
        transacao.setValor(dados.valor());
        transacao.setMovimentacao(Movimentacao.SAQUE);
        

        contaDestino.setSaldo(saldoContaDestinoSubtraida);

        transacao.setIdContaDestino(contaDestino);
        transacao.setIdContaOrigem(null);
        repository.save(transacao);

        return transacao;
    }

    // DEPOSITO
    public Transacao deposito(DadosTransacao dados) {
        var contaDestino = repositoryConta.getReferenceById(dados.idContaDestino());
        BigDecimal SaldoContaDestinoSomada = contaDestino.getSaldo().add(dados.valor());

        var transacao = new Transacao();
        transacao.setTipo(dados.tipo());
        transacao.setValor(dados.valor());
        transacao.setMovimentacao(Movimentacao.DEPOSITO);

        contaDestino.setSaldo(SaldoContaDestinoSomada);
        
        transacao.setIdContaDestino(contaDestino);
        transacao.setIdContaOrigem(null);
        
        repository.save(transacao);

        return transacao;

    }

    // Gets
    public List<ListarTransacao> exibirTransacao() {
        return repository.findAll().stream().map(ListarTransacao:: new).toList();
    }

    
    public ListarTransacao exibirIdTransacao(long id) {
        var transacao = repository.getReferenceById(id);
        return new ListarTransacao(transacao);
    }
}
