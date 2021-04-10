package com.algaworks.algafood.api.input_model.input_model_to_domain;

import com.algaworks.algafood.api.input_model.CozinhaImputModel;
import com.algaworks.algafood.domain.entitys.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputModelToDomainModel {
    
    @Autowired
    ModelMapper modelMapper;

    public Cozinha toCozinhaDomainModel(CozinhaImputModel cozinhaImputModel){
        return modelMapper.map(cozinhaImputModel, Cozinha.class);
    }

    public void copyPropertiesToCozinhaDomainModel(CozinhaImputModel cozinhaImputModel, Cozinha cozinha){
        modelMapper.map(cozinhaImputModel, cozinha);
    }
}
