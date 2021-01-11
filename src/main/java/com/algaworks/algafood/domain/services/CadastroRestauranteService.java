package com.algaworks.algafood.domain.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.RestauranteNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadrastroCozinhaService cozinhaService;
	
	
	//---------SERVICE_ADICIONAR_RESTAURANTES----------//
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
	public Restaurante serviceAtualizar(Long id, Restaurante rest) {
		Restaurante restaurante = serviceBuscar(id);
		BeanUtils.copyProperties(rest, restaurante, "id", "formasPagamento", "endereco", "dataCriacao", "produtos");
		return serviceAdicionar(restaurante);
	}
}
