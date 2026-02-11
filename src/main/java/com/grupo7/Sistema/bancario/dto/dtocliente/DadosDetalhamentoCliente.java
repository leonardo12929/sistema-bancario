package com.grupo7.Sistema.bancario.dto.dtocliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.entity.Cliente;


public record DadosDetalhamentoCliente(
    long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    LocalDate dataNascimento,
    LocalDateTime dataCadastro,
    String endereco
) {
    
    public DadosDetalhamentoCliente(Cliente dados) {
        this(dados.getId(), dados.getNome(), dados.getCpf(), dados.getEmail(),
         dados.getTelefone(), dados.getDataNascimento(), dados.getDataCadastro(), dados.getEndereco());
    }
}
