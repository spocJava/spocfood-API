package com.algaworks.algafood.api.DTO;

import com.algaworks.algafood.domain.entitys.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaDTO {

    @NotNull
    @JsonView(RestauranteView.ResumoView.class)
    private Long id;

    @NotBlank
    @JsonView(RestauranteView.ResumoView.class)
    private String nome;

}
