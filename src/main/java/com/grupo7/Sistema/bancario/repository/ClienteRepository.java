package com.grupo7.Sistema.bancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo7.Sistema.bancario.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByAtivoTrue();
}