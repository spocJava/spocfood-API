package com.algaworks.algafood.domain.exeptions;

public class EntidadeNaoEncontradaExecption extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaExecption(String mensagem) {
		super(mensagem);
	}
	
}
