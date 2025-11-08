package com.grupo7.Sistema.bancario.contabancaria;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotBlank
    private String numeroConta;
    @PositiveOrZero
    private BigDecimal saldo;
    @NotBlank
    private String titular;

    public ContaBancaria(ContaBancaria dados) {
        this.numeroConta = dados.getNumeroConta();
        this.saldo = dados.getSaldo();
        this.titular = dados.getTitular();
    }
}
