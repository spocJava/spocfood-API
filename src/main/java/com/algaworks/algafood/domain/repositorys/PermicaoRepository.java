package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import com.algaworks.algafood.domain.models.Permicao;


public interface PermicaoRepository {
	
	List<Permicao> listar();
	Permicao buscar(Long Id);
	Permicao salvar(Permicao cozinha);
	void remover(Permicao cozinha);
}
