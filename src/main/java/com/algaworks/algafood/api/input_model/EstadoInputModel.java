package com.algaworks.algafood.api.input_model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInputModel {
    
    @NotBlank
    private String nome;
    
}
