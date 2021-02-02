package com.algaworks.algafood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public void excluir(Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(
						String.format("Não existe uma Forma de pagamento com o codigo %d.", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					String.format("A entidade com o codigo %d não pode ser removida, pois está em uso.", id));
		}
	}
}
