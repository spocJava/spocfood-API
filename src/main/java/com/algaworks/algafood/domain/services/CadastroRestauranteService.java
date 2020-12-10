package com.algaworks.algafood.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.Cozinha;
import com.algaworks.algafood.domain.models.Restaurante;
import com.algaworks.algafood.domain.repositorys.CozinhaRepository;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	//---------SERVICE_ADICIONAR_RESTAURANTES----------//
	public Restaurante adicionar(Restaurante restaurante) {
		Long id = restaurante.getCozinha().getId();
		Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);
		
		if(cozinhaOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaExecption(
					String.format("Não existe uma cozinha com o código %d.", id));
		}
		restaurante.setCozinha(cozinhaOptional.get());
		return restauranteRepository.save(restaurante);
	}
}
