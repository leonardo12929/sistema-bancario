package com.grupo7.Sistema.bancario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.contabancaria.ContaBancaria;
import com.grupo7.Sistema.bancario.contabancaria.ContaBancariaRepository;
import com.grupo7.Sistema.bancario.contabancaria.DadosAtualizarContaBancaria;
import com.grupo7.Sistema.bancario.contabancaria.DadosCadastroContaBancaria;
import com.grupo7.Sistema.bancario.contabancaria.DadosListarContaBancaria;
import com.grupo7.Sistema.bancario.cliente.Cliente;
import com.grupo7.Sistema.bancario.cliente.ClienteRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/ContaBancaria")
public class ControllerContaBancaria {

    private final ContaBancariaRepository repository;
    private final ClienteRepository clienteRepository;

    public ControllerContaBancaria(ContaBancariaRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }
    @PostMapping
    @Transactional
    public void Cadastrar(@RequestBody @Valid DadosCadastroContaBancaria dados) {
        Cliente titular = clienteRepository.getReferenceById(dados.titular());
        ContaBancaria contaBancaria = new ContaBancaria(dados);
        contaBancaria.setTitular(titular);
        repository.save(contaBancaria);

    }

    @GetMapping
    public List<DadosListarContaBancaria> listar() {
        return repository.findAllByAtivoTrue().stream().map(DadosListarContaBancaria::new).toList();

    }
    @GetMapping("/{id}")
    public DadosListarContaBancaria listarId(@PathVariable long id) {
        var conta = repository.getReferenceById(id);
        return new DadosListarContaBancaria(conta);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarContaBancaria dados) {
        var contaBancaria = repository.getReferenceById(dados.id());
        contaBancaria.atulizarContaBancaria(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        var contaBancaria = repository.getReferenceById(id);
        contaBancaria.setAtivo(false);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("ativar/{id}")
    @Transactional
    public void reativar(@PathVariable long id) {
        var contaBancaria = repository.getReferenceById(id);
        contaBancaria.setAtivo(true);
    }
}
