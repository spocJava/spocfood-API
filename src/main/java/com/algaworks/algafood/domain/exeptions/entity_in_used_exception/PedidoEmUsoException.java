package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class PedidoEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public PedidoEmUsoException(String mensagem) {
		super(mensagem);
	}

	public PedidoEmUsoException(Long id) {
		this(String.format("O pedido com o ID -> %d não pode ser excluída, pois está em uso.", id));
	}

}
