package com.algaworks.algalog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController // declarar que é um controller pra funcionar os mapeamento
public class ClienteController {

	@GetMapping("/clientes") //mapeamento
	public List<Cliente> listar() {
		
		Cliente cliente1 = new Cliente(); 
		
		cliente1.setId(1l);
		cliente1.setNome("Luiz");
		cliente1.setEmail("jvdematos004@gmail.com");
		cliente1.setTelefone("(61) 9 9423-0955");
		
		var cliente2 = new Cliente();
		
		cliente2.setId(2l);
		cliente2.setNome("Paulo");
		cliente2.setEmail("teste@gmail.com");
		cliente2.setTelefone("(44) 9 9323-0942");
		
		return Arrays.asList(cliente1, cliente2);
	}

}
