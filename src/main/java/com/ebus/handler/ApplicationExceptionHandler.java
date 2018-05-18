package com.ebus.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagem = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String detalhes = ex.getCause().getMessage();
		
		return handleExceptionInternal(ex, new Erro(mensagem, detalhes), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro {
		
		private String mensagem;
		
		private String detalhes;

		public Erro(String mensagem, String detalhes) {
			this.mensagem = mensagem;
			this.detalhes = detalhes;
		}
		
		public String getMensagem() {
			return mensagem;
		}
		
		public String getDetalhes() {
			return detalhes;
		}
		
		@Override
		public String toString() {
			return mensagem;
		}
		
	}
	
}
