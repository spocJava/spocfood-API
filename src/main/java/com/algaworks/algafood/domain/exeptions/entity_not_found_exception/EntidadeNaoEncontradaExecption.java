package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

import com.algaworks.algafood.domain.exeptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaExecption extends NegocioException{
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaExecption(String mensagem) {
		super(mensagem);
	}
	
}
