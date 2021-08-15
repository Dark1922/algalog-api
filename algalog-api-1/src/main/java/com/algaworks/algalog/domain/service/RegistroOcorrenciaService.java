package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;
	
	//registrar uma ocorrencia
	@Transactional
	public Ocorrencia registar(Long id, String descricao ) {
		
		Entrega entrega = buscaEntregaService.buscar(id);
		
		return entrega.adicionarOcorrencia(descricao); 
	}
	
}
