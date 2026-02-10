package com.grupo7.Sistema.bancario.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.contabancaria.ContaBancaria;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn (name = "idContaOrigem")
    private ContaBancaria idContaOrigem;
    @ManyToOne
    @JoinColumn (name = "idContaDestino")
    private ContaBancaria idContaDestino;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;

    public Transacao() {
        this.status = status.COMFIRMADA;
        this.dataTransacao = dataTransacao.now();
    }
}
