package com.algaworks.algafood.api.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDTO {

    @NotNull
    private Long Id;

    @NotBlank
    private String nome;

}
