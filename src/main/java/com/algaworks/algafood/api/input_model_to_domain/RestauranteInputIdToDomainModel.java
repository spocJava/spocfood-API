package com.algaworks.algafood.api.input_model_to_domain;

import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.domain.entitys.Cidade;
import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.entitys.Restaurante;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
