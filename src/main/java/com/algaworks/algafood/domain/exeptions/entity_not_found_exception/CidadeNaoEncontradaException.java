package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradaException(Long id) {
		super(String.format("Não existe uma cidade com o ID -> %d, na base de dados.", id));
	}
}
