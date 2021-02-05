package com.algaworks.algafood.api.domain_to_DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.domain.entitys.Restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModel {

	@Autowired
	ModelMapper modelMapper;
    
    public RestauranteModel toRestauranteDTO(Restaurante restaurante){
		return modelMapper.map(restaurante, RestauranteModel.class);
	}

	public List<RestauranteModel> toListRestauranteDTOs(List<Restaurante> restaurantes){
		return restaurantes.stream()
			.map(restaurante -> toRestauranteDTO(restaurante))
			.collect(Collectors.toList());
	}
}
