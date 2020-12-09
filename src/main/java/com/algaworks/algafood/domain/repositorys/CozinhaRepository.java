package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import com.algaworks.algafood.domain.models.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> listar();
	Cozinha buscar(Long Id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
}
