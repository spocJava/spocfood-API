package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.UsuarioDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.UsuarioModel;
import com.algaworks.algafood.api.input_model.SenhaUsuarioInputModel;
import com.algaworks.algafood.api.input_model.UsuarioInputModel;
import com.algaworks.algafood.api.input_model.UsuarioInputModelUpDate;
import com.algaworks.algafood.api.input_model.input_model_to_domain.UsuarioInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Usuario;
import com.algaworks.algafood.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioModel usuarioModel;
	@Autowired
	private UsuarioInputModelToDomainModel inputModelToDomainModel;

	
	//---- Listar todos os usuários da base de dados ----->
	@GetMapping
	public List<UsuarioDTO> listar(){
		return usuarioModel.usuarioDTOList(usuarioService.getAllUsers());
	}
	
	//---- Buscar um usuário pelo seu id ---->
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscarById(@PathVariable Long usuarioId) {
		return usuarioModel.toUsuarioDTO(usuarioService.getUserById(usuarioId));
	}

	//---- Adicionar um novo usuario na base de dados ---->
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO addUsuario(@RequestBody @Valid UsuarioInputModel inputModel){
		Usuario usuario = inputModelToDomainModel.toDomainModel(inputModel);
		return usuarioModel.toUsuarioDTO(usuarioService.addUsuario(usuario));
	}

	//---- Atualiza um usuario presente na base de dados ---->
	@PutMapping("/{usuarioId}")
	public UsuarioDTO upDateUsuario(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInputModelUpDate inputModel){
		return usuarioModel.toUsuarioDTO(usuarioService.upDateUsuario(usuarioId, inputModel));
	}

	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void upDateSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaUsuarioInputModel senha){
		usuarioService.upDateSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	}

	//---- Remove um usuario pela seu id da base de dados ---->
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeUsuario(@PathVariable Long usuarioId){
		usuarioService.deleteUserById(usuarioId);
	}
}
