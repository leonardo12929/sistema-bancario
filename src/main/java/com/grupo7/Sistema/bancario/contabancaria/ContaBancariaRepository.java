package com.grupo7.Sistema.bancario.contabancaria;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    
    List<ContaBancaria> findAllByAtivoTrue();
    Optional<ContaBancaria> findByIdAndAtivoTrue(Long id);
}
