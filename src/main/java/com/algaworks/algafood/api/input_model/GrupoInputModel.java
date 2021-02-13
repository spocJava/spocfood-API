package com.algaworks.algafood.api.input_model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInputModel {

    @NotBlank
    private String nome;

}
