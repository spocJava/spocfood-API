package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algaworks.algafood.domain.exeptions.NegocioException;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoExeption extends NegocioException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoExeption(String mensagem) {
		super(mensagem);
	}
}
