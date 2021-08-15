package com.algaworks.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//classe pra rodar o modelmapper e economizar linhas de códigos usadas no EntregaController

@Configuration //configuração de beans do spring
public class ModelMapperConfig {

	@Bean //ele instancia ele inicializa e configura um bran que sera gerenciado pelo spring
	//podendo fazer injeções em outras classe
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
