package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class EstadoEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public EstadoEmUsoException(String mensagem) {
		super(mensagem);
	}

	public EstadoEmUsoException(Long id) {
		this(String.format("O estado com o ID -> %d não pode ser excluído, pois está em uso.", id));
	}

}
