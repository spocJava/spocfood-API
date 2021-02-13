package com.algaworks.algafood.domain.services;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.api.input_model.UsuarioInputModel;
import com.algaworks.algafood.api.input_model.UsuarioInputModelUpDate;
import com.algaworks.algafood.api.input_model_to_domain.UsuarioInputModelToDomainModel;
import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.UsuarioEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.UsuarioNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.entitys.Usuario;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioInputModelToDomainModel inputModelToDomainModel;

	
	//---- Lista todos os usuários do sistema ---->
	public List<Usuario> getAllUsers(){
		return usuarioRepository.findAll();
	}
	
	//---- Busca um Usuário pelo seu id ----->
	public Usuario getUserById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}
	
	//---- Adiciona um novo Usuário na base de dados ---->
	@Transactional
	public Usuario addUsuario(Usuario usuario) {
		//--buscar um usuário
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
			throw new NegocioException(String.format("Já existe um usuário com esse e-mail %s cadastrado" +
					" no sistema", usuario.getEmail()));
		}

		return usuarioRepository.save(usuario);
	}

	//---- Atualiza um usuario na base de dados ---->
	public Usuario upDateUsuario(Long id, UsuarioInputModelUpDate inputModelUpDate){
		Usuario usuarioAtual = getUserById(id);
		BeanUtils.copyProperties(inputModelUpDate, usuarioAtual, "senha");
		return addUsuario(usuarioAtual);
	}

	//---- Atualização de senha do usuario
	@Transactional
	public void upDateSenha(Long id, String senhaAtual, String novaSenha){
		Usuario usuario = getUserById(id);
		String senha = usuario.getSenha();
		if(!senha.equals(senhaAtual)){
			throw new NegocioException("A senha não bate com a senha atual do usuário");
		}
		usuario.setSenha(novaSenha);
	}
	
	//---- Remove um Usuário pelo seu id ---->
	@Transactional
	public void deleteUserById(Long id) {
		try {
			usuarioRepository.deleteById(id);
			usuarioRepository.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new UsuarioEmUsoException(id);
		}
	}
}
