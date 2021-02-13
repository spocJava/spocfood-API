package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class GrupoEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public GrupoEmUsoException(String mensagem) {
		super(mensagem);
	}

	public GrupoEmUsoException(Long id) {
		this(String.format("O grupo com o ID -> %d não pode ser excluída, pois está em uso.", id));
	}

}
