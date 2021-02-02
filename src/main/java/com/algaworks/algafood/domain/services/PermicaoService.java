package com.algaworks.algafood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.PermicaoRepository;

@Service
public class PermicaoService {

	@Autowired
	private PermicaoRepository permicaoRepository;
	
	//------EXCLUIR_PERMIÇÃO_SERVICE--------//
	@Transactional
	public void excluir(Long id) {
		try {
			permicaoRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(
					String.format("A entidade permição com o código %d, não foi encontrada.", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					String.format("A permição de código %d não pode ser removida, pois está em uso.", id));
		}
	}
}
