package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.entitys.Permicao;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.PermicaoNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.PermicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermicaoService {

	@Autowired
	private PermicaoRepository permicaoRepository;

	public List<Permicao> permissoes(){
		return permicaoRepository.findAll();
	}

	public Permicao getPermissao(Long permicaoId){
		return permicaoRepository.findById(permicaoId).orElseThrow(() -> new PermicaoNaoEncontradaException(permicaoId));
	}

}
