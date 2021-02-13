package com.algaworks.algafood.api.input_model_to_domain;

import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.domain.entitys.Cidade;
import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.entitys.Restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RestauranteInputModelToDomainModel {

	@Autowired
	ModelMapper modelMapper;
    
    //--converte RestauranteInputModel em RestauranteModel
	public Restaurante toRestauranteDamainModel(RestauranteInputModel restauranteInputModel){
		return modelMapper.map(restauranteInputModel, Restaurante.class);
	}

	//--Copia as propriedades de um RestauranteInputModel para um objeto restaurante na atualização
	public void copyInputToModel(RestauranteInputModel restauranteInputModel, Restaurante restaurante){
		//--evita o erro em que o modelmapper pensa que estamos adicionando uma nova cozinha ao invéz de so referenciar
		restaurante.setCozinha(new Cozinha());

		if(restaurante.getEndereco() != null){
			restaurante.getEndereco().setCidade(new Cidade());
		}
		modelMapper.map(restauranteInputModel, restaurante);
	}
}
