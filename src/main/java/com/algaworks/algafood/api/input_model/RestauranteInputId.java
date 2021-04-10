package com.algaworks.algafood.api.input_model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteInputId {

    @NotNull
    private Long id;

}
