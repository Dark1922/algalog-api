package com.algaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)//n vai incluir pra campos nulo
@Getter
@Setter
public class Problema {
	
	private Integer status;
	private OffsetDateTime dataHora; //hora q foi gerada o problema
	private String titulo;
	private List<Campo> campos;
	
	
	@AllArgsConstructor
	@Getter
	public static class Campo {
		private String nome;
		private String mensagem;
	}
}
