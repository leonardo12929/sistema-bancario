package com.grupo7.Sistema.bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.contabancaria.ContaBancaria;
import com.grupo7.Sistema.bancario.contabancaria.ContaBancariaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ContaBancaria")
public class Controller {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @PostMapping
    public void Salvar(@RequestBody @Valid ContaBancaria dados) {
        contaBancariaRepository.save(new ContaBancaria(dados));

    }
}
