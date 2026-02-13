package com.grupo7.Sistema.bancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo7.Sistema.bancario.entity.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}