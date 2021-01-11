package com.algaworks.algafood.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.entitys.Grupo;
import com.algaworks.algafood.domain.services.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	
	//--- Listar todos os grupos --->
	@GetMapping
	public List<Grupo> listar(){
		return grupoService.getAllGroups();
	}
	

	//--- Buscar um grupo pelo seu id --->
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Grupo> grupoOptional = grupoService.getGroupById(id);
			
		if(grupoOptional.isPresent()) {
			return ResponseEntity.ok(grupoOptional.get());
		}
			return ResponseEntity.notFound().build();
		}
}
