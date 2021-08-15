package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;
import model.input.EntregaInput;
import model.representation.EntregaModel;



@AllArgsConstructor
@Component //componente do spring
public class EntregaAssembler {
	
	private ModelMapper modelMapper;
	
	public	EntregaModel toModel(Entrega entrega) {
		
		return modelMapper.map(entrega, EntregaModel.class);
	}

	//coleção de representation model
	//lista de entrega pra entrega model :: pega o parametro do tomodel pra ele 
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
		
		return entregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList()); //list de um entregaModel
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
