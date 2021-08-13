package com.algaworks.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

@Repository  //repositorio que gerencia as entidades
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);//nome especifico
	List<Cliente> findByNomeContaining(String nome); //usa o like procura por partes do nome
	Optional<Cliente> findByEmail(String email);
}
