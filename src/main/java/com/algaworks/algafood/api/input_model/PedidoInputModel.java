package com.algaworks.algafood.api.input_model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class PedidoInputModel {

    @Valid
    @NotNull
    private RestauranteInputId restaurante;

    @Valid
    @NotNull
    private EnderecoModel enderecoEntrega;

    @Valid
    @NotNull
    private FormaPagamentoInputId formaPagamento;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItensPedidoInput> items;

}
