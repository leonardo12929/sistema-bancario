package com.grupo7.Sistema.bancario.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.enums.enumtransacao.Movimentacao;
import com.grupo7.Sistema.bancario.enums.enumtransacao.StatusTransacao;
import com.grupo7.Sistema.bancario.enums.enumtransacao.TipoTransacao;

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
    @Enumerated(EnumType.STRING)
    private Movimentacao movimentacao;
    @ManyToOne
    @JoinColumn (name = "idContaOrigem")
    private ContaBancaria idContaOrigem;
    @ManyToOne
    @JoinColumn (name = "idContaDestino")
    private ContaBancaria idContaDestino;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;

    public Transacao() {
        this.status = StatusTransacao.COMFIRMADA;
        this.dataTransacao = LocalDateTime.now();
    }
}
