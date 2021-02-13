package com.algaworks.algafood.domain.services;

import java.util.List;

import com.algaworks.algafood.api.input_model.GrupoInputModel;
import com.algaworks.algafood.api.input_model_to_domain.GrupoInputModelToDomainModel;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.GrupoEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.GrupoNaoEncontradoException;
import org.springframework.beans.BeanUtils;
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
	@Autowired
	GrupoInputModelToDomainModel inputModelToDomainModel;

	//---- Listar grupos ---->
	public List<Grupo> getAllGroups(){
		return grupoRepository.findAll();
	}

	//---- Buscar um Grupo pelo seu id ---->
	public Grupo getGroupById(Long id) {
		return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}

	//---- Adiciona um novo grupo ----->
	public Grupo upDate(Long id, GrupoInputModel inputModel){
		Grupo grupoAtual = getGroupById(id);
		inputModelToDomainModel.copyProperties(inputModel, grupoAtual);
		return addGroup(grupoAtual);
	}

	//---- Adiciona um novo grupo ---->
	@Transactional
	public Grupo addGroup(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	//---- Remove um grupo pelo seu id ---->
	@Transactional
	public void deleteGrupById(Long id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new GrupoEmUsoException(id);
		}
	}
	
	
}
