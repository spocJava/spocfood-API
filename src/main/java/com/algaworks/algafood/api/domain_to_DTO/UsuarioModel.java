package com.algaworks.algafood.api.domain_to_DTO;

import com.algaworks.algafood.api.DTO.UsuarioDTO;
import com.algaworks.algafood.domain.entitys.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModel {

    @Autowired
    ModelMapper modelMapper;

    public UsuarioDTO toUsuarioDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> usuarioDTOList(Collection<Usuario> usuarios){
        return usuarios.stream()
                .map(usuario -> toUsuarioDTO(usuario))
                .collect(Collectors.toList());
    }
}
