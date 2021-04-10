package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.PermicaoDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.PermicaoModel;
import com.algaworks.algafood.domain.entitys.Grupo;
import com.algaworks.algafood.domain.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permicoes")
public class GrupoPermicaoController {

    @Autowired
    private GrupoService grupoService;
    @Autowired
    private PermicaoModel permicaoModel;

    @GetMapping
    public List<PermicaoDTO> permicaos(@PathVariable Long grupoId){
        Grupo grupo = grupoService.getGroupById(grupoId);
        return  permicaoModel.toListPermicaoDTO(grupo.getPermicoes());
    }

    @PutMapping("/{permicaoId}")
    public void addPermicao(@PathVariable Long grupoId, @PathVariable Long permicaoId){
        grupoService.addPermicao(grupoId, permicaoId);
    }

    @DeleteMapping ("/{permicaoId}")
    public void removePermicao(@PathVariable Long grupoId, @PathVariable Long permicaoId){
        grupoService.removerPermicao(grupoId, permicaoId);
    }
}
