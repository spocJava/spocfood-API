package com.algaworks.algafood.api.input_model.input_model_to_domain;

import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.domain.entitys.Restaurante;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class RestauranteInputIdToDomainModel {

	private final ModelMapper modelMapper;
    
    //--converte RestauranteInputModel em RestauranteModel
	public Restaurante toRestauranteDamainModel(RestauranteInputModel restauranteInputModel){
		return modelMapper.map(restauranteInputModel, Restaurante.class);
	}

}
