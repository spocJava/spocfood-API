package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class CozinhaEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public CozinhaEmUsoException(String mensagem) {
		super(mensagem);
	}

	public CozinhaEmUsoException(Long id) {
		this(String.format("A cozinha com o ID -> %d não pode ser excluída, pois está em uso.", id));
	}

}
