package com.algaworks.algafood.infrastructure.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.models.Restaurante;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{
	/**
	 * Injeta pra gente um bean do gerenciador de entidades.
	 */
	@Autowired
	private EntityManager manager;
	
	/**
	 * Metodo para listar todos os restaurantes da base de dados.
	 */
	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		restaurante = buscar(restaurante.getId());
		manager.remove(restaurante);
	}
	
}
