package com.algaworks.algafood.api.DTO;

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
