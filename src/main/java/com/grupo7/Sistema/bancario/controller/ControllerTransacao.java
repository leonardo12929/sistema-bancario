package com.grupo7.Sistema.bancario.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.infra.TransacaoService;
import com.grupo7.Sistema.bancario.transacao.DadosFazerTransacao;

@RestController
@RequestMapping("/Transacao")
public class ControllerTransacao {

    private TransacaoService service;

    public ControllerTransacao(TransacaoService service) {
        this.service = service;
    }
    @PostMapping
    public void fazerTransacao(@RequestBody DadosFazerTransacao dados) {
        service.fazerTransacao(dados);
    }
}
