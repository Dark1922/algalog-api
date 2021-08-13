package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long id) {
		
		return clienteRepository.findById(id)
				   .orElseThrow(() -> new NegocioException("Cliente não encontrado."));
	}
	
	//se ela der certo vai rodar normal , se der algo errado n funciona
	@Transactional //deve ser executado dentro de uma transação
	public Cliente salvar(Cliente cliente) {
		
		//valida se o emaiol está em uso e se é diferente do cliente que ja tem ele
		//por causa da atualização o cliente pode alterar seu email
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso)  {//se tiver em uso passa essa mensagem
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
}
