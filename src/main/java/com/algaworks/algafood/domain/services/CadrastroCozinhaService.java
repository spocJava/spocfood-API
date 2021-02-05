/**
 * Clase de serviço - Para cadrastro de cozinhas
 * Nessa clase vc pode implementar regras mais complexas para as operações
 * relacionadas a cozinhas. Deixando o controler mais tranquilo e sem acesso direto ao repositorio.
 */
package com.algaworks.algafood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.input_model.CozinhaImputModel;
import com.algaworks.algafood.api.input_model_to_domain.CozinhaInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.CozinhaRepository;

@Service
public class CadrastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository; 

	@Autowired
	CozinhaInputModelToDomainModel cozinhaInputModelToDomainModel;
	
	//---------ADICIONAR-----------//
	@Transactional
	public Cozinha serviceSalvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	
	//---------BUSCAR-----------//
	public Cozinha serviceBuscar(Long id) {
		return cozinhaRepository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}
	
	
	//---------ATUALIZAR-----------//
	public Cozinha serviceAtualizar(Long id, CozinhaImputModel cozinha) {
		Cozinha cozinhaAtual = serviceBuscar(id);
		cozinhaInputModelToDomainModel.copyPropertiesToCozinhaDomainModel(cozinha, cozinhaAtual);
		return serviceSalvar(cozinhaAtual);
	}
	
	
	//---------EXCLUIR-----------//
	@Transactional
	public void serviceExcluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();//--força o delete de forma imediata na dase de dados
			
		}catch(EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e){
			throw new CozinhaEmUsoException(id);
		}
	}
	
	
}
