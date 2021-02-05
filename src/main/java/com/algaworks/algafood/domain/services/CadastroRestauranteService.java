package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.api.input_model_to_domain.RestauranteInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.RestauranteNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadrastroCozinhaService cozinhaService;

	@Autowired
	RestauranteInputModelToDomainModel restInputToModel;
	
	
	//---------SERVICE_ADICIONAR_RESTAURANTES----------//
	@Transactional
	public Restaurante serviceAdicionar(Restaurante restaurante) {
		Long id = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.serviceBuscar(id);
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	
	//---------SERVICE_BUSCAR_RESTAURANTES----------//
	public Restaurante serviceBuscar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradaException(id));
	}
	
	
	//---------SERVICE_ATUALIZAR_RESTAURANTES----------//
	public Restaurante serviceAtualizar(Long id, RestauranteInputModel restInput) {
		Restaurante restauranteAtual = serviceBuscar(id);
        restInputToModel.copyInputToModel(restInput, restauranteAtual);
		return serviceAdicionar(restauranteAtual);
	}
}
