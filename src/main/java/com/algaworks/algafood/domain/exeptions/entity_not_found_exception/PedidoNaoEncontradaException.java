package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradaException(String codigo) {
		super(String.format("Não existe um pedido com o código -> %s, na base de dados.", codigo));
	}
}
