package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
		super(String.format("Não existe um produto com o ID -> %d, no restaurante de código %s.", produtoId, restauranteId));
	}
}
