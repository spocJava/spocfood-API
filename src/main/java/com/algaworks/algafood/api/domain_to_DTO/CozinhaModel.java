package com.algaworks.algafood.api.domain_to_DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.domain.entitys.Cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CozinhaModel {
    
    @Autowired
    ModelMapper modelMapper;

    //--CozinhaModel to CozinhaDTO
    public CozinhaModel toCozinhaDTO(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaModel.class);
    }

    //--Retorna uma lista de cozinhaDTOs
    public List<CozinhaModel> toListCozinhaDTO(List<Cozinha> cozinhas){
        return cozinhas.stream().map(cozinha -> toCozinhaDTO(cozinha))
            .collect(Collectors.toList());
    }
}
