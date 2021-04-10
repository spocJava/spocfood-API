package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.domain.entitys.Permicao;
import com.algaworks.algafood.domain.services.PermicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
