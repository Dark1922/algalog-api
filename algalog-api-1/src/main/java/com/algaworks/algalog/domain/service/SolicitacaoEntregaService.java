package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor //faz injeção do repositório pelo construtor
@Service //componente spring com semantica de um serviço
public class SolicitacaoEntregaService {

	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregaRepository;
	
	   @Transactional 
		public Entrega solicitar(Entrega entrega) {
			
		   //consulta se o cliente que foi consultado na entrega se ele existe
		  Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		   
		  entrega.setCliente(cliente);
		   entrega.setStatus(StatusEntrega.PENDENTE); 
		   entrega.setDataPedido(OffsetDateTime.now()); //data atual
		   
		   return entregaRepository.save(entrega);
		}
	
	
}
