package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public GrupoNaoEncontradoException(Long id) {
		super(String.format("Não existe um grupo com o ID -> %d, na base de dados.", id));
	}
}
