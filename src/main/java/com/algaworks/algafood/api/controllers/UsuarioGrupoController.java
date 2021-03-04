package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.GrupoDTO;
import com.algaworks.algafood.api.domain_to_DTO.GrupoModel;
import com.algaworks.algafood.domain.entitys.Usuario;
import com.algaworks.algafood.domain.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
@AllArgsConstructor
public class UsuarioGrupoController {

    private final UsuarioService usuarioService;
    private final GrupoModel grupoModel;

    @GetMapping
    public List<GrupoDTO> usuarioGrupos(@PathVariable Long usuarioId){
        Usuario usuario = usuarioService.getUserById(usuarioId);
        return grupoModel.grupoDTOList(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.addGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.removeGrupo(usuarioId, grupoId);
    }
}
