package com.algaworks.algafood.api.DTO.domain_to_DTO;

import com.algaworks.algafood.api.DTO.GrupoDTO;
import com.algaworks.algafood.domain.entitys.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModel {

    @Autowired
    ModelMapper modelMapper;

    public GrupoDTO toGrupoDTO(Grupo grupo){
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    public List<GrupoDTO> grupoDTOList(Collection<Grupo> grupos){
        return grupos.stream()
                .map(grupo -> toGrupoDTO(grupo))
                .collect(Collectors.toList());
    }
}
