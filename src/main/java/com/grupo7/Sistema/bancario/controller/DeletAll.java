package com.grupo7.Sistema.bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.repository.ClienteRepository;
import com.grupo7.Sistema.bancario.repository.ContaBancariaRepository;
import com.grupo7.Sistema.bancario.repository.TransacaoRepository;

@RestController
@RequestMapping("DeletAll")
public class DeletAll {

    ClienteRepository clienteRepository;
    ContaBancariaRepository contaBancariaRepository;
    TransacaoRepository transacaoRepository;

    public DeletAll(ClienteRepository dados1, ContaBancariaRepository dados2, TransacaoRepository dados3) {
        this.clienteRepository = dados1;
        this.contaBancariaRepository = dados2;
        this.transacaoRepository = dados3;
    }

    @DeleteMapping
    public void deletAll() {
        transacaoRepository.deleteAllInBatch();
        contaBancariaRepository.deleteAllInBatch();
        clienteRepository.deleteAllInBatch();
    }
}
