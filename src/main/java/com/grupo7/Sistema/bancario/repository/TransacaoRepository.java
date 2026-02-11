package com.grupo7.Sistema.bancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo7.Sistema.bancario.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}