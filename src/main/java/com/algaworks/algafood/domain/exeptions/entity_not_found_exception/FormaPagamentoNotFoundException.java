package com.algaworks.algafood.domain.exeptions.entity_not_found_exception;

public class FormaPagamentoNotFoundException extends EntidadeNaoEncontradaExecption{
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNotFoundException(String mensagem) {
		super(mensagem);
	}

	public FormaPagamentoNotFoundException(Long id) {
		super(String.format("Não existe uma forma de pagamento com o ID -> %d, na base de dados.", id));
	}
}
