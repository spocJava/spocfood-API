package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class UsuarioEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public UsuarioEmUsoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioEmUsoException(Long id) {
		this(String.format("Usuario com o ID -> %d não pode ser excluída, pois está em uso.", id));
	}

}
