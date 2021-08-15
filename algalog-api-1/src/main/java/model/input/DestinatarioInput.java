package model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioInput {
	
	@NotNull
	private String nome;
	
	@NotNull
	private String logradouro;
	
	@NotNull
	private String numero;
	
	@NotNull
	private String complemento;
	
	@NotNull
	private String bairro;
	

}
