package com.algaworks.algalog.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //que tem que especificar pra poder usar
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include //inclui o id pro hashcode
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank //impedi que a propriedade seje nulo ou vazio
	@Size(max = 60, min = 4)
	private String nome;
	
	@NotBlank 
	@Email() //para que valide que seje um formato de email correto
	@Size(max = 255)
	private String email;
	
	@NotBlank 
	@Size(max = 20)
	private String telefone;

}
