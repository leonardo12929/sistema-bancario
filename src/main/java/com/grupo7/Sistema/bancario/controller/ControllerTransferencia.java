package com.grupo7.Sistema.bancario.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.dto.dtotransacao.DadosFazerTransferencia;
import com.grupo7.Sistema.bancario.service.servicetransferencia.TransferenciaService;

@RestController
@RequestMapping("/Transacao")
public class ControllerTransferencia {

    private TransferenciaService service;

    public ControllerTransferencia(TransferenciaService service) {
        this.service = service;
    }
    @PostMapping
    public void Transferencia(@RequestBody DadosFazerTransferencia dados) {
        service.transferencia(dados);
    }
}
