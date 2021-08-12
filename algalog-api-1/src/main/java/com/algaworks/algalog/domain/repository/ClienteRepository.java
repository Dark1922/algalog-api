package com.algaworks.algalog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algalog.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
