package com.algaworks.algalog.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController // declarar que é um controller pra funcionar os mapeamento
public class ClienteController {

	@PersistenceContext  //injeta uma variavel do maneger na variavel declarada
	private EntityManager entityManager;
	
	@GetMapping("/clientes") //mapeamento
	public List<Cliente> listar() {
		
		return entityManager.createQuery(" from Cliente ", Cliente.class)
				.getResultList();
		
	}

}
