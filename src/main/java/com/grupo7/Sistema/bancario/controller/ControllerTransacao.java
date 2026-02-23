package com.grupo7.Sistema.bancario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.dto.dtotransacao.ListarTransacao;
import com.grupo7.Sistema.bancario.dto.dtotransacao.TransacaoOutros;
import com.grupo7.Sistema.bancario.dto.dtotransacao.DadosTransacao;
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
    public void transferencia(@RequestBody DadosTransacao dados) {
        service.transferencia(dados);
    }
    @PostMapping("/saque")
    public void saque(@RequestBody DadosTransacao dados) {
        service.saque(dados);
    }
    @GetMapping
    public List<ListarTransacao> exibirTransacao() {
        return service.exibirTransacao();
    }
}
