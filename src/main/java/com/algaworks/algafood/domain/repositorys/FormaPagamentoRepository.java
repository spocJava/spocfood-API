package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import com.algaworks.algafood.domain.models.FormaPagamento;

public interface FormaPagamentoRepository {
	 
	List<FormaPagamento> listar();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);
}
