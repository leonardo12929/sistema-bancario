package com.grupo7.Sistema.bancario.service.servicetransferencia;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo7.Sistema.bancario.dto.dtotransacao.DadosFazerTransferencia;
import com.grupo7.Sistema.bancario.entity.Transferencia;
import com.grupo7.Sistema.bancario.repository.ContaBancariaRepository;
import com.grupo7.Sistema.bancario.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

    private TransferenciaRepository repository;
    private ContaBancariaRepository repositoryConta;
    
    public TransferenciaService(TransferenciaRepository repository, ContaBancariaRepository repositoryConta ) {
        this.repository = repository;
        this.repositoryConta = repositoryConta;
    }
    
    @Transactional
    public void transferencia(DadosFazerTransferencia dados) {
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
        var transferencia = new Transferencia();
        transferencia.processar();
        transferencia.setTipo(dados.tipo());
        transferencia.setValor(dados.valor());
        
        
        contaOrigem.setSaldo(valorContaOrigemSubtraida);
        contaDestino.setSaldo(valorContaDestinoSomada);

        transferencia.setIdContaOrigem(contaOrigem);
        transferencia.setIdContaDestino(contaDestino);

        
        repository.save(transferencia);
    } 
}
