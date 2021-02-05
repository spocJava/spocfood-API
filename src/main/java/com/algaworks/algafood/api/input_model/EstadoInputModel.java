package com.algaworks.algafood.api.input_model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInputModel {
    
    @NotBlank
    private String nome;
    
}
