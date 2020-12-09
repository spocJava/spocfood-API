package com.algaworks.algafood.domain.exeptions;

public class EntidadeEmUsoExeption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoExeption(String mensagem) {
		super(mensagem);
	}
}
