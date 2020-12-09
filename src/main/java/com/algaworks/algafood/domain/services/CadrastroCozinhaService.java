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

import com.algaworks.algafood.domain.exeptions.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.Cozinha;
import com.algaworks.algafood.domain.repositorys.CozinhaRepository;

@Service
public class CadrastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository; 
	
	//---------ADICIONAR-----------//
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
	
	//---------EXCLUIR-----------//
	public void excluir(Long id) {
		try {
			cozinhaRepository.remover(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(
					String.format("Não existe uma cozinha de código %d na base de dados. ", id));
			
		}catch(DataIntegrityViolationException e){
			throw new EntidadeEmUsoExeption(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso.", id));
		}
	}
	
	//---------BUSCAR-----------//
	public Cozinha buscar(Long id) {
		return cozinhaRepository.buscar(id);
	}
}
