package com.grupo7.Sistema.bancario.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.cliente.Cliente;
import com.grupo7.Sistema.bancario.cliente.ClienteRepository;
import com.grupo7.Sistema.bancario.cliente.DadosCadastroCliente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Cliente")
public class ControllerCliente {
    private final ClienteRepository repository;

    public ControllerCliente(ClienteRepository repository) {
        this.repository = repository;
    }
    @PostMapping
    @Transactional
    public void Cadastrar(@RequestBody @Valid DadosCadastroCliente dados) {
        repository.save(new Cliente(dados));
    }
 }
