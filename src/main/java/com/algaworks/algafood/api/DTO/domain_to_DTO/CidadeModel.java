package com.algaworks.algafood.api.DTO.domain_to_DTO;

import com.algaworks.algafood.api.DTO.CidadeDTO;
import com.algaworks.algafood.domain.entitys.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
