package com.algaworks.algafood.api.model_DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteDTO {
    
    private Long id;
    private String nome;
    private Double taxaFrete;
    private CozinhaDTO cozinha;
    
}
