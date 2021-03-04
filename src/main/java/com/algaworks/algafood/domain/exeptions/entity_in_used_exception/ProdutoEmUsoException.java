package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class ProdutoEmUsoException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public ProdutoEmUsoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoEmUsoException(Long id) {
		this(String.format("O produto com o ID -> %d não pode ser excluída, pois está em uso.", id));
	}

}
