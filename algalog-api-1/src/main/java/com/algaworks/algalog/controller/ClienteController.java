package com.algaworks.algalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

//gera um construtor com todas propriedades que tem em nossa clase no caso clientrepository
@AllArgsConstructor
@RequestMapping("/clientes") // vai mapear automaticamente as rota como /clientes
@RestController // declarar que é um controller pra funcionar os mapeamento
public class ClienteController {

	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;

	@GetMapping() // mapeamento /clientes
	public List<Cliente> listar() {

		return clienteRepository.findAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {

		return clienteRepository.findById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

	}
	
	@PostMapping //post pra criar user
	@ResponseStatus(HttpStatus.CREATED)//status de criação qnd cria
	//@RequestBody corpo da requisição transformar oq foi passado em um objeto java
     public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
    	 
		return catalogoClienteService.salvar(cliente);
     } 
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id,
			@RequestBody Cliente cliente) {
		
		//ta negando se o cliente não existe
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id); //atribuir o id ao objeto cliente pra força uma atualização 
		
		cliente = catalogoClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")//é utilizado quando o valor da variável é passada diretamente na URL id
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		
		//se o cliente que tá exluindo n existir notfound
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(id);
		
		return ResponseEntity.noContent().build(); //código 204 qnd n está voltando nada e exclui
	}
}
