package com.grupo7.Sistema.bancario.contabancaria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String titular;
    private boolean ativo = true;

    public ContaBancaria(DadosCadastroContaBancaria dados) {
        this.numeroConta = "4345";
        this.agencia = "666";
        this.saldo = BigDecimal.valueOf(0.00);
        this.tipoConta = dados.tipoConta();
        this.status = StatusConta.ATIVADA;
        this.dataCriacao = LocalDateTime.now();
        this.titular = dados.titular();        
    }

    public void atulizarContaBancaria(@RequestBody @Valid DadosAtualizarContaBancaria dados) {
        if (dados.titular() != null) {
            this.titular = dados.titular();
        }
        
    }
}
