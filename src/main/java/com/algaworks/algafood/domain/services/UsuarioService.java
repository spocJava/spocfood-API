package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.input_model.UsuarioInputModelUpDate;
import com.algaworks.algafood.domain.entitys.Grupo;
import com.algaworks.algafood.domain.entitys.Usuario;
import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.UsuarioEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.repositorys.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final GrupoService grupoService;

	
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

	//---- Adiciona o usuário a um grupo de permissão ---->
	@Transactional
	public void addGrupo(Long grupoId, Long usuarioId){
		Usuario usuario = getUserById(usuarioId);
		Grupo grupo = grupoService.getGroupById(grupoId);
		usuario.addGrupo(grupo);
	}

	//---- Remove usuário de um grupo de permissão ---->
	@Transactional
	public void removeGrupo(Long usuarioId, Long grupoId){
		Usuario usuario = getUserById(usuarioId);
		Grupo grupo = grupoService.getGroupById(grupoId);
		usuario.removeGrupo(grupo);
	}
}
