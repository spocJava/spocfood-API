package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import com.algaworks.algafood.domain.models.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante retaurante);
	void remover(Restaurante retaurante);
}
