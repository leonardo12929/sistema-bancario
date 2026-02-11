package com.grupo7.Sistema.bancario.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestBody;

import com.grupo7.Sistema.bancario.dto.dtocontabancaria.DadosAtualizarContaBancaria;
import com.grupo7.Sistema.bancario.dto.dtocontabancaria.DadosCadastroContaBancaria;
import com.grupo7.Sistema.bancario.enums.enumcontabancaria.StatusConta;
import com.grupo7.Sistema.bancario.enums.enumcontabancaria.TipoConta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "contabancaria")
public class ContaBancaria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="numeroConta")
    private String numeroConta;
    private String agencia;
    private BigDecimal saldo;
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
    @Enumerated(EnumType.STRING)
    private StatusConta status;
    private LocalDateTime dataCriacao;
    private String apelido;
    private boolean ativo = true;
    @ManyToOne
    @JoinColumn(name = "titularId")
    private Cliente titular;


    public ContaBancaria(DadosCadastroContaBancaria dados) {
        this.numeroConta = "4345";
        this.agencia = "666";
        this.saldo = BigDecimal.valueOf(500.00);
        this.tipoConta = dados.tipoConta();
        this.status = StatusConta.ATIVADA;
        this.dataCriacao = LocalDateTime.now();
        this.apelido = dados.apelido();
    }

    public void atualizarContaBancaria(@RequestBody @Valid DadosAtualizarContaBancaria dados) {
        if (dados.apelido() != null) {
            this.apelido = dados.apelido();
        }
        
    }
        
}
