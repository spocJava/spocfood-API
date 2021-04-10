package com.algaworks.algafood.api.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoDTO {
    
    @NotNull
    private Long id;

    @NotBlank
    private String nome;
}
