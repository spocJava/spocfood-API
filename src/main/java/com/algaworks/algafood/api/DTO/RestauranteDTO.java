package com.algaworks.algafood.api.DTO;

import com.algaworks.algafood.domain.entitys.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteDTO {

    @JsonView(RestauranteView.ResumoView.class)
    private Long id;

    @JsonView(RestauranteView.ResumoView.class)
    private String nome;

    @JsonView(RestauranteView.ResumoView.class)
    private Double taxaFrete;

    @JsonView(RestauranteView.ResumoView.class)
    private CozinhaDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
    
}
