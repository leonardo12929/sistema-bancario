package com.grupo7.Sistema.bancario.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.grupo7.Sistema.bancario.enums.enumtransferencia.StatusTransferencia;
import com.grupo7.Sistema.bancario.enums.enumtransferencia.TipoTransferencia;
import com.grupo7.Sistema.bancario.service.base.Transacao;

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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Transferencia extends Transacao{
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
    private TipoTransferencia tipo;
    @Enumerated(EnumType.STRING)
    private StatusTransferencia status;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;

    @Override
    public void processar() {
        this.status = StatusTransferencia.CONFIRMADA;
        this.dataTransacao = LocalDateTime.now();
    }
}
