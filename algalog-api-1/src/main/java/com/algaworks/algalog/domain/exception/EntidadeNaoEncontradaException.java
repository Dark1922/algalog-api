package com.algaworks.algalog.domain.exception;

//que vai dar a mensagem de error tratada e o erro que colocamos pra ela
public class EntidadeNaoEncontradaException extends NegocioException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
	
	

}
