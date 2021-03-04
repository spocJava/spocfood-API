package com.algaworks.algafood.domain.services;

import java.util.List;

import com.algaworks.algafood.api.input_model.GrupoInputModel;
import com.algaworks.algafood.api.input_model_to_domain.GrupoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Permicao;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.GrupoEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.GrupoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algafood.domain.entitys.Grupo;
import com.algaworks.algafood.domain.repositorys.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	GrupoInputModelToDomainModel inputModelToDomainModel;
	@Autowired
	private PermicaoService permicaoService;

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

	//---- ADICIONAR PERMIÇÃO ---->
	@Transactional
	public void addPermicao(Long grupoId, Long permicaoId){
		Grupo grupo = getGroupById(grupoId);
		Permicao permicao = permicaoService.getPermissao(permicaoId);
		grupo.addPermicao(permicao);
	}

	//---- REMOVER PERMIÇÃO ---->
	@Transactional
	public void removerPermicao(Long grupoId, Long permicaoId){
		Permicao permicao = permicaoService.getPermissao(permicaoId);
		Grupo grupo = getGroupById(grupoId);
		grupo.removePermicao(permicao);
	}
	
}
