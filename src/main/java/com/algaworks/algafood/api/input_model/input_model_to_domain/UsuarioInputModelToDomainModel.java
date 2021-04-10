package com.algaworks.algafood.api.input_model.input_model_to_domain;

import com.algaworks.algafood.api.input_model.UsuarioInputModel;
import com.algaworks.algafood.domain.entitys.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputModelToDomainModel {

    @Autowired
    ModelMapper modelMapper;

    public Usuario toDomainModel(UsuarioInputModel inputModel){
        return modelMapper.map(inputModel, Usuario.class);
    }

    public void copyPropertis(UsuarioInputModel inputModel, Usuario usuario){
        modelMapper.map(inputModel, usuario);
    }
}
