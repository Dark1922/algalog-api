package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.domain.model.Cliente;

import lombok.AllArgsConstructor;
import model.representation.ClienteCrudRepresentation;

@AllArgsConstructor
@Component //componente do spring
public class ClienteAssembler {

	private ModelMapper modelMapper;

	
	// método de buscar
		public ClienteCrudRepresentation toModel(Cliente cliente) {
			return modelMapper.map(cliente, ClienteCrudRepresentation.class);
		}
		
	// Lister todos coleção do model cliente
	public List<ClienteCrudRepresentation> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream()
				.map(this::toModel)
				.collect(Collectors.toList()); 
	} 

	public Cliente toEntity(ClienteCrudRepresentation clienteCrudRepresentation) {
		return modelMapper.map(clienteCrudRepresentation, Cliente.class);
	}

}
