package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class ItemPedidoNaoEncontradaException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public ItemPedidoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public ItemPedidoNaoEncontradaException(Long id) {
		super(String.format("Não existe um item de pedido com o ID -> %d, na base de dados.", id));
	}
}
