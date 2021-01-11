package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long id) {
		this(String.format("Não existe uma cozinha com o ID -> %d, na base de dados.", id));
	}

}
