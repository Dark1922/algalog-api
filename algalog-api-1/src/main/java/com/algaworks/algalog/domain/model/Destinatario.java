package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable //embutida com o destinatario do model entrega
public class Destinatario {

	//n precisa de id pq está embutido com a entrega essa classe destinatário
	
	@NotNull
	@Column(name = "destinatario_nome")
	private String nome;
	
	@NotNull
	@Column(name = "destinatario_logradouro")
	private String logradouro;
	
	@NotNull
	@Column(name = "destinatario_numero")
	private String numero;
	
	@NotNull
	@Column(name = "destinatario_complemento")
	private String complemento;
	
	@NotNull
	@Column(name = "destinatario_bairro")
	private String bairro;
	
}
