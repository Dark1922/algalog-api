package com.algaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice //propositor de tratar os erros global
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problema.Campo> campos = new ArrayList<>();
		
		//pega todos erros que foram atribuidos para essa requisição
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			//String mensagem = error.getDefaultMessage();
			
			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value()); //retorna o código de status http
		problema.setDataHora(OffsetDateTime.now()); //na hora que ocorreu 
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento corretamente e tente novamente");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)//método por trata essa exception
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST; //status 400
		
		Problema problema = new Problema();
		problema.setStatus(status.value()); //retorna o código de status http
		problema.setDataHora(OffsetDateTime.now()); //na hora que ocorreu 
		problema.setTitulo(ex.getMessage()); //a mensagem passa na regra de negócio
		
		//o corpo passa o problema e o header http status e a requisição
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);	
}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)//método por trata essa exception
	public ResponseEntity<Object> handleEntidadeNaoEncontrada
	(EntidadeNaoEncontradaException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND; //status 404
		
		Problema problema = new Problema();
		problema.setStatus(status.value()); //retorna o código de status http
		problema.setDataHora(OffsetDateTime.now()); //na hora que ocorreu 
		problema.setTitulo(ex.getMessage()); //a mensagem passa na regra de negócio
		
		//o corpo passa o problema e o header http status e a requisição
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);	
}
}
