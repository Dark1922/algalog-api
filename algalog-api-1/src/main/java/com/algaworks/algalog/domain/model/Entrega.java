package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include //apenas o id
	private Long id;
	
	@ManyToOne //muitos pedidos pra um cliente 
	private Cliente cliente;
	
	//abstrair os dados do distinatario para uma outra classe porem mapeando para
	//tabela da entidade entrega
	@Embedded 
	private Destinatario destinatario;
	
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING) //tipo da numeração é string
	private StatusEntrega status;
	private LocalDateTime dataPedido;	
	private LocalDateTime dataFinalizacao;
}