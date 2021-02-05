package com.algaworks.algafood.api.input_model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaImputModel {

    @NotBlank
    private String nome;

}
