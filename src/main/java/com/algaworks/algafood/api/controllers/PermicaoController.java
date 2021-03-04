package com.algaworks.algafood.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.entitys.Permicao;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.PermicaoRepository;
import com.algaworks.algafood.domain.services.PermicaoService;

@RestController
@RequestMapping("/permissoes")
public class PermicaoController {

	@Autowired
	private PermicaoService permicaoService;

	@GetMapping
	public List<Permicao> permicaos(){
		return permicaoService.permissoes();
	}
	
	//-----CONTROLLER_BUSCAR_PERMIÇÕES------//
	@GetMapping("/{permissaoId}")
	public Permicao buscar(@PathVariable Long permissaoId) {
		return permicaoService.getPermissao(permissaoId);
	}
}
