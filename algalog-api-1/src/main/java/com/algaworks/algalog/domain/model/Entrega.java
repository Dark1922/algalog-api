package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import domain.ValidationGroups;
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
	
	@Valid //validar o objeto do cliente tb em cascata
	@NotNull
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class) //converte
	@ManyToOne //muitos pedidos pra um cliente 
	private Cliente cliente;
	
	//abstrair os dados do distinatario para uma outra classe porem mapeando para
	//tabela da entidade entrega
	@NotNull
	@Embedded 
	@Valid
	private Destinatario destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING) //tipo da numeração é string
	@JsonProperty(access = Access.READ_ONLY)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;	
	
	@JsonProperty(access = Access.READ_ONLY) //somente leitura
	private OffsetDateTime dataFinalizacao;
	
	//o inverso da ocorrencia e o dono do mapeamento do relacionamento de lá
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();

	public Ocorrencia adicionarOcorrencia(String descricao) {

		Ocorrencia ocorrencia  = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now()); //data de agora
		ocorrencia.setEntrega(this); //this é a própria entrega dessa classe Entrega
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	} 
}
