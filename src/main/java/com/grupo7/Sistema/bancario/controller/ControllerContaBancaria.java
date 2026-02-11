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
import org.springframework.web.util.UriComponentsBuilder;

import com.grupo7.Sistema.bancario.dto.dtocontabancaria.DadosAtualizarContaBancaria;
import com.grupo7.Sistema.bancario.dto.dtocontabancaria.DadosCadastroContaBancaria;
import com.grupo7.Sistema.bancario.dto.dtocontabancaria.DadosDetalhamentoConta;
import com.grupo7.Sistema.bancario.dto.dtocontabancaria.DadosListarContaBancaria;
import com.grupo7.Sistema.bancario.entity.ContaBancaria;
import com.grupo7.Sistema.bancario.repository.ClienteRepository;
import com.grupo7.Sistema.bancario.repository.ContaBancariaRepository;

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
    public ResponseEntity<DadosDetalhamentoConta> Cadastrar(@RequestBody @Valid DadosCadastroContaBancaria dados, UriComponentsBuilder uriBuilder) {
        var titular = clienteRepository.getReferenceById(dados.titular());
        var contaBancaria = new ContaBancaria(dados);
        contaBancaria.setTitular(titular);
        repository.save(contaBancaria);

        var uri = uriBuilder.path("/ContaBancaria/{id}").buildAndExpand(contaBancaria.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoConta(contaBancaria));

    }

    @GetMapping
    public ResponseEntity<List<DadosListarContaBancaria>> listar() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListarContaBancaria::new).toList();
        return ResponseEntity.ok(lista);

    }
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConta> listarId(@PathVariable long id) {
        var conta = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoConta(conta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConta> atualizar(@RequestBody @Valid DadosAtualizarContaBancaria dados) {
        var contaBancaria = repository.getReferenceById(dados.id());
        contaBancaria.atualizarContaBancaria(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConta(contaBancaria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        var contaBancaria = repository.getReferenceById(id);
        contaBancaria.setAtivo(false);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable long id) {
        var contaBancaria = repository.getReferenceById(id);
        contaBancaria.setAtivo(true);

        return ResponseEntity.noContent().build();
    }
}
