package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class CidadeEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public CidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

	public CidadeEmUsoException(Long id) {
		this(String.format("A cidade com o ID -> %d não pode ser excluída, pois está em uso.", id));
	}

}
