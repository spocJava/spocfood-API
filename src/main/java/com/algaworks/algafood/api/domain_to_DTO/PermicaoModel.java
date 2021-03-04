package com.algaworks.algafood.api.domain_to_DTO;

import com.algaworks.algafood.api.DTO.PermicaoDTO;
import com.algaworks.algafood.domain.entitys.Permicao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PermicaoModel {

    private ModelMapper modelMapper;

    public PermicaoDTO toPermicaoDTO(Permicao permicao){
        return modelMapper.map(permicao, PermicaoDTO.class);
    }

    public List<PermicaoDTO> toListPermicaoDTO(Collection<Permicao> permicoes){
        return  permicoes.stream()
                .map(permicao -> toPermicaoDTO(permicao))
                .collect(Collectors.toList());
    }
}
