package com.algaworks.algafood.api.input_model.input_model_to_domain;

import com.algaworks.algafood.api.input_model.CidadeInputModel;
import com.algaworks.algafood.domain.entitys.Cidade;
import com.algaworks.algafood.domain.entitys.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputModelToDomainModel {
    
    @Autowired
    ModelMapper modelMapper;

    public Cidade convertToCidadeDomainModel(CidadeInputModel cidadeInputModel){
        return modelMapper.map(cidadeInputModel, Cidade.class);
    }

    //--copia as propriedades de um input model para um bean do domain model (cidade) na atualização
    public void copyPropertiesToDomainModel(CidadeInputModel inputModel, Cidade cidade){
        cidade.setEstado(new Estado());//--evita uma exception (não pode criar um novo estado)
        modelMapper.map(inputModel, cidade);
    }
}
