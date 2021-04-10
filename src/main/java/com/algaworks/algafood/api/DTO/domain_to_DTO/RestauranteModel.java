package com.algaworks.algafood.api.DTO.domain_to_DTO;

import com.algaworks.algafood.api.DTO.RestauranteDTO;
import com.algaworks.algafood.domain.entitys.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModel {

	@Autowired
	ModelMapper modelMapper;
    
    public RestauranteDTO toRestauranteDTO(Restaurante restaurante){
		return modelMapper.map(restaurante, RestauranteDTO.class);
	}

	public List<RestauranteDTO> toListRestauranteDTOs(List<Restaurante> restaurantes){
		return restaurantes.stream()
			.map(restaurante -> toRestauranteDTO(restaurante))
			.collect(Collectors.toList());
	}
}
