package com.algaworks.algafood.domain.exeptions.entity_in_used_exception;

public class FormaPagamentoInUseException extends EntidadeEmUsoExeption{
	private static final long serialVersionUID = 1L;

	public FormaPagamentoInUseException(String mensagem) {
		super(mensagem);
	}

	public FormaPagamentoInUseException(Long id) {
		this(String.format("A forma de pagamento com o ID -> %d não pode ser excluída, pois está em uso.", id));
	}

}
