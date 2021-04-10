package com.algaworks.algafood.api.input_model.input_model_to_domain;

import com.algaworks.algafood.api.input_model.GrupoInputModel;
import com.algaworks.algafood.domain.entitys.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputModelToDomainModel {

    @Autowired
    ModelMapper modelMapper;

    public Grupo toDomainModel(GrupoInputModel inputModel){
        return modelMapper.map(inputModel, Grupo.class);
    }

    public void copyProperties(GrupoInputModel inputModel, Grupo grupo){
        modelMapper.map(inputModel, grupo);
    }
}
