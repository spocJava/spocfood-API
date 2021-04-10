package com.algaworks.algafood.api.input_model.input_model_to_domain;

import com.algaworks.algafood.api.input_model.EstadoInputModel;
import com.algaworks.algafood.domain.entitys.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputModelToDomainModel {
    
    @Autowired
    ModelMapper modelMapper;

    public Estado toEstadoDomainModel(EstadoInputModel estadoInputModel){
        return modelMapper.map(estadoInputModel, Estado.class);
    }

    public void copyPropertiesToDomainModel(EstadoInputModel estadoInputModel, Estado estado){
        modelMapper.map(estadoInputModel, estado);
    }
}
