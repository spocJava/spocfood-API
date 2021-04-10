package com.algaworks.algafood.api.input_model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class EnderecoModel {

    @NotBlank
    private String cep;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private cidadeId cidade;


    @Getter @Setter
    public static  class cidadeId{
        @NotNull
        private Long id;
    }

}
