package model.representation;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCrudRepresentation {

	@Id
	private Long id;
	
	@NotBlank //impedi que a propriedade seje nulo ou vazio
	private String nome;
	
	@Email() //para que valide que seje um formato de email correto
	@NotBlank 
	private String email;
	
	@NotBlank 
	private String telefone;

}
