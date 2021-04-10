package com.algaworks.algafood.api.input_model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeInputModel {
    
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoId estado;
    
    
    @Getter
    @Setter
    public static class EstadoId {

        @NotNull
        private Long id;
    }
}
