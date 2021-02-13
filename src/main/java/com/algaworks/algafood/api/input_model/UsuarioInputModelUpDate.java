package com.algaworks.algafood.api.input_model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInputModelUpDate {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
