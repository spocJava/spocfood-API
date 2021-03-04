package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class PermicaoNaoEncontradaException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public PermicaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public PermicaoNaoEncontradaException(Long id) {
		super(String.format("Não existe uma permição com o ID -> %d, na base de dados.", id));
	}
}
