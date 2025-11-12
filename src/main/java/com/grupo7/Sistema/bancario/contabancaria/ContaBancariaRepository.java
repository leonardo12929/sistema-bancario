package com.grupo7.Sistema.bancario.contabancaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    
    List<ContaBancaria> findAllByAtivoTrue();
}
