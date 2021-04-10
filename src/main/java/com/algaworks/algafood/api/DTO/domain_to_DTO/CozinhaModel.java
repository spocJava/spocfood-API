package com.algaworks.algafood.api.DTO.domain_to_DTO;

import com.algaworks.algafood.api.DTO.CozinhaDTO;
import com.algaworks.algafood.domain.entitys.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CozinhaModel {
    
    @Autowired
    ModelMapper modelMapper;

    //--CozinhaModel to CozinhaDTO
    public CozinhaDTO toCozinhaDTO(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    //--Retorna uma lista de cozinhaDTOs
    public List<CozinhaDTO> toListCozinhaDTO(List<Cozinha> cozinhas){
        return cozinhas.stream().map(cozinha -> toCozinhaDTO(cozinha))
            .collect(Collectors.toList());
    }
}
