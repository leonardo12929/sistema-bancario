package com.grupo7.Sistema.bancario.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grupo7.Sistema.bancario.dto.dtocliente.DadosAtualizarCliente;
import com.grupo7.Sistema.bancario.dto.dtocliente.DadosCadastroCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDateTime dataCadastro;
    private String endereco;
    @OneToMany(mappedBy = "titular")
    @JsonIgnore
    private List<ContaBancaria> contas;
    private boolean ativo = true;

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataNascimento = dados.dataNascimento();
        this.dataCadastro = LocalDateTime.now();
        this.endereco = dados.endereco();
        
    }

    public void atualizar (DadosAtualizarCliente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.dataNascimento() != null) {
            this.dataNascimento = dados.dataNascimento();
        }
        if (dados.endereco() != null) {
            this.endereco = dados.endereco();
        }
    }
}
    