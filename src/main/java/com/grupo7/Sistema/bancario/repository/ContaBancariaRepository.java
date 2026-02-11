package com.grupo7.Sistema.bancario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo7.Sistema.bancario.entity.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    
    List<ContaBancaria> findAllByAtivoTrue();
    Optional<ContaBancaria> findByIdAndAtivoTrue(Long id);
}
