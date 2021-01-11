package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long id) {
		this(String.format("Não existe um estado com o ID -> %d, na base de dados.", id));
	}

}
