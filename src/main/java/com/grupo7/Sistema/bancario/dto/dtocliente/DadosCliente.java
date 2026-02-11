package com.grupo7.Sistema.bancario.dto.dtocliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.entity.Cliente;

public record DadosCliente(
    Long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    LocalDate dataNascimento,
    LocalDateTime dataCadastro,
    String endereco

) {
    public DadosCliente(Cliente cliente) {
        this(
            cliente.getId(), cliente.getNome(), cliente.getCpf(),
            cliente.getEmail(), cliente.getTelefone(), cliente.getDataNascimento(),
            cliente.getDataCadastro(), cliente.getEndereco());
    }
}