package com.algaworks.algafood.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.entitys.Grupo;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;

	//---- Listar grupos ---->
	public List<Grupo> getAllGroups(){
		return grupoRepository.findAll();
	}
	
	//---- Adiciona um novo grupo ---->
	@Transactional
	public Grupo addGroup(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	//---- Buscar um Grupo pelo seu id ---->
	public Optional<Grupo> getGroupById(Long id) {
		try {
			return grupoRepository.findById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(String.format(
					"Não existe um Grupo com ID=%d, na base de dados.", id));
		}
	}
	
	
	//---- Remove um grupo pelo seu id ---->
	@Transactional
	public void deleteGrupById(Long id) {
		try {
			grupoRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(String.format(
					"Não existe um Grupo com ID=%d, na base de dados.", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(
					"Não foi possível remover o 'grupo' de ID=%d. Pois está em uso por outra entidade.", id));
		}
	}
	
	
}
