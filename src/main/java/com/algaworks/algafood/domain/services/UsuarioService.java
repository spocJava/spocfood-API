package com.algaworks.algafood.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.entitys.Usuario;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//---- Lista todos os usuários do sistema ---->
	public List<Usuario> getAllUsers(){
		return usuarioRepository.findAll();
	}
	
	//---- Busca um Usuário pelo seu id ----->
	public Optional<Usuario> getUserById(Long id) {
		try {
			return usuarioRepository.findById(id); 
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(String.format(
					"Não existe um Usuario com ID=%d, na base de dados.", id));
		}
	}
	
	//---- Adiciona um novo Usuário na base de dados ---->
	public Usuario addUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	
	//---- Remove um Usuário pelo seu id ---->
	public void deleteUserById(Long id) {
		try {
			usuarioRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(String.format(
					"Não existe um Usuario com ID=%d, na base de dados.", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(
					"Não foi possível remover o 'Usuário' de ID=%d. Pois está em uso por outra entidade.", id));
		}
		
	}
}
