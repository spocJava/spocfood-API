package com.algaworks.algafood.api.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GrupoDTO {

    private Long id;
    private String nome;

}
