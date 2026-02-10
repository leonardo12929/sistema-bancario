package com.grupo7.Sistema.bancario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.grupo7.Sistema.bancario.cliente.Cliente;
import com.grupo7.Sistema.bancario.cliente.ClienteRepository;
import com.grupo7.Sistema.bancario.cliente.DadosAtualizarCliente;
import com.grupo7.Sistema.bancario.cliente.DadosCadastroCliente;
import com.grupo7.Sistema.bancario.cliente.DadosDetalhamentoCliente;
import com.grupo7.Sistema.bancario.cliente.DadosCliente;

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
    public ResponseEntity<DadosDetalhamentoCliente> cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        repository.save(cliente);

        var uri = uriBuilder.path("/Cliente{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));


    }
    @GetMapping
    public ResponseEntity<List<DadosCliente>> listar() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosCliente:: new).toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCliente> listaId(@PathVariable long id ) {
        var cliente = repository.getReferenceById(id);  
        return ResponseEntity.ok(new DadosCliente(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosCliente> atualizar( @RequestBody @Valid DadosAtualizarCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizar(dados);
        
        return ResponseEntity.ok(new DadosCliente(cliente));
    }

    @PutMapping("/Inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable long id) {
        var cliente = repository.getReferenceById(id);
        cliente.setAtivo(false);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/Reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable long id) {
        var cliente = repository.getReferenceById(id);
        cliente.setAtivo(true);

        return ResponseEntity.noContent().build();
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    } 
    
    
}
