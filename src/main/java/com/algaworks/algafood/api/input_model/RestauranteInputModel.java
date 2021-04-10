package com.algaworks.algafood.api.input_model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class RestauranteInputModel {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private Double taxaFrete;

    @Valid
    @NotNull
    private CozinhaId cozinha;

    @Valid
    @NotNull
    private EnderecoModel endereco;

    @Getter
    @Setter
    public static class CozinhaId{

        @NotNull
        private Long id;
    }

}
