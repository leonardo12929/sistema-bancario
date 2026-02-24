package com.grupo7.Sistema.bancario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.grupo7.Sistema.bancario.dto.dtotransacao.ListarTransacao;
import com.grupo7.Sistema.bancario.entity.ContaBancaria;
import com.grupo7.Sistema.bancario.dto.dtotransacao.DadosTransacao;
import com.grupo7.Sistema.bancario.service.servicetransacao.TransacaoService;

@RestController
@RequestMapping("/Transacao")
public class ControllerTransacao {

    private TransacaoService service;

    public ControllerTransacao(TransacaoService service) {
        this.service = service;
    }
    
    
    @PostMapping("/transferencia")
    public ResponseEntity<ListarTransacao> transferencia(@RequestBody DadosTransacao dados, UriComponentsBuilder urikBuilder) {
        var transacao =  service.transferencia(dados);
        var uri = urikBuilder.path("/Transacao/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new ListarTransacao(transacao));
    }
    
    
    @PostMapping("/saque")
    public ResponseEntity<ListarTransacao> saque(@RequestBody DadosTransacao dados, UriComponentsBuilder uriBuilder) {
        var transacao = service.saque(dados);
        var uri = uriBuilder.path("/Transacao/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new ListarTransacao(transacao));
    }
    
    
    @PostMapping("/deposito")
    public ResponseEntity<ListarTransacao> deposito(@RequestBody DadosTransacao dados, UriComponentsBuilder uriBuilder) {
        var transacao = service.deposito(dados);
        var uri = uriBuilder.path("/Transacao/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new ListarTransacao(transacao));
    }
    
    @GetMapping
    public ResponseEntity<List<ListarTransacao>> exibirTransacao() {
        return ResponseEntity.ok(service.exibirTransacao());
    }
    @GetMapping("{id}")
    public ResponseEntity<ListarTransacao> exibirIdTransacao(@PathVariable long id) {
        
        return ResponseEntity.ok(service.exibirIdTransacao(id));
    }

}
