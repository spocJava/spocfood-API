package com.algaworks.algafood.api.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
