package com.algaworks.algafood.api.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoDTO {

    private Long id;

    @NotBlank
    private String descricao;
}
