package com.algaworks.algafood.api.input_model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInputModel {

    @NotNull
    private Long id;
}
