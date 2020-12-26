package com.algaworks.algafood.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.models.Usuario;
import com.algaworks.algafood.domain.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	//---- Listar todos os usuários da base de dados ----->
	@GetMapping
	public List<Usuario> listar(){
		return usuarioService.getAllUsers();
	}
	
	//---- Buscar um usuário pelo seu id ---->
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarById(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.getUserById(id);
		
		if(usuarioOptional.isPresent()) {
			return ResponseEntity.ok(usuarioOptional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
