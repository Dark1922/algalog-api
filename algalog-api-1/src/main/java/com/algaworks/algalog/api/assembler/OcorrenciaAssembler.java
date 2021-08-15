package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;
import model.representation.OcorrenciaModel;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper modelMapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	}
	
	//apartir de uma lista de ocorrencia
	public List<OcorrenciaModel>toCollectionModel(List<Ocorrencia> ocorrencias) {
		
		return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
	}
}
