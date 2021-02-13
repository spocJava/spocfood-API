package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNaoEncontradoException(Long id) {
		super(String.format("Não existe um usuário com o ID -> %d, na base de dados.", id));
	}
}
