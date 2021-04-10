package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.UsuarioDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.UsuarioModel;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.services.CadastroRestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
@AllArgsConstructor
public class RestauranteUsuarioResponsavel {

    private final CadastroRestauranteService cadastroRestauranteService;
    private final UsuarioModel usuarioModel;

    @GetMapping
    public List<UsuarioDTO> listaResponsaveis(@PathVariable Long restauranteId){
        Restaurante restaurante = cadastroRestauranteService.serviceBuscar(restauranteId);
        return usuarioModel.usuarioDTOList(restaurante.getResponsaveis());
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addResponsavel(@PathVariable Long restauranteId, @PathVariable  Long usuarioId){
        cadastroRestauranteService.addResponsavel(restauranteId, usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerResponsavel(@PathVariable Long restauranteId,@PathVariable Long usuarioId){
        cadastroRestauranteService.removerResponsavel(restauranteId, usuarioId);
    }
}
