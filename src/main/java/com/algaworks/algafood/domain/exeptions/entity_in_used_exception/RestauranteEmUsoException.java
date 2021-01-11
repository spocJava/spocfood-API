package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class RestauranteEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public RestauranteEmUsoException(String mensagem) {
		super(mensagem);
	}

	public RestauranteEmUsoException(Long id) {
		this(String.format("O restaurante com o ID -> %d não pode ser excluído, pois está em uso.", id));
	}

}
