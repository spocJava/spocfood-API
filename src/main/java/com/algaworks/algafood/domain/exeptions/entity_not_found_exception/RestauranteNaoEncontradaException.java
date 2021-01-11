package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public RestauranteNaoEncontradaException(Long id) {
		super(String.format("Não existe um restaurante com o ID -> %d, na base de dados.", id));
	}
}
