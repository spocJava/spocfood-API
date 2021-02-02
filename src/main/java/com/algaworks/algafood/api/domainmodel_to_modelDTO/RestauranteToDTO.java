package com.algaworks.algafood.api.domainmodel_to_modelDTO;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.model_DTO.CozinhaDTO;
import com.algaworks.algafood.api.model_DTO.RestauranteDTO;
import com.algaworks.algafood.domain.entitys.Restaurante;

import org.springframework.stereotype.Component;

@Component
public class RestauranteToDTO {
    
    public RestauranteDTO toRestauranteDTO(Restaurante restaurante){

		RestauranteDTO restauranteDTO = new RestauranteDTO();
		CozinhaDTO cozinhaDTO = new CozinhaDTO();

		cozinhaDTO.setId(restaurante.getCozinha().getId());
		cozinhaDTO.setNome(restaurante.getCozinha().getNome());

		restauranteDTO.setId(restaurante.getId());
		restauranteDTO.setNome(restaurante.getNome());
		restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteDTO.setCozinha(cozinhaDTO);

		return restauranteDTO;
	}

	public List<RestauranteDTO> toListRestauranteDTOs(List<Restaurante> restaurantes){
		return restaurantes.stream()
			.map(restaurante -> toRestauranteDTO(restaurante))
			.collect(Collectors.toList());
	}
}
