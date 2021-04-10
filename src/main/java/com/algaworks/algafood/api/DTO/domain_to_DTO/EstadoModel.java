package com.algaworks.algafood.api.DTO.domain_to_DTO;

import com.algaworks.algafood.api.DTO.EstadoDTO;
import com.algaworks.algafood.domain.entitys.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModel {
    
    @Autowired
    ModelMapper modelMapper;

    //--estdo domain to estado dto
    public EstadoDTO toEstadoDTO(Estado estado){
        return modelMapper.map(estado, EstadoDTO.class);
    }

    //--Cria uma lista de estadoDTOs
    public List<EstadoDTO> toListEstadoDTO(List<Estado> estados){
        return estados.stream()
            .map(estado -> toEstadoDTO(estado))
            .collect(Collectors.toList());
    }
}
