package com.algaworks.algafood.api.domain_to_DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.DTO.CidadeDTO;
import com.algaworks.algafood.domain.entitys.Cidade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeModel {
    
    @Autowired
    ModelMapper modelMapper;

    public CidadeDTO toCidadeDTO(Cidade cidade){
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toListCidadeDTO(List<Cidade> cidades){
        return cidades.stream()
            .map(cidade -> toCidadeDTO(cidade))
            .collect(Collectors.toList());
    }
}
