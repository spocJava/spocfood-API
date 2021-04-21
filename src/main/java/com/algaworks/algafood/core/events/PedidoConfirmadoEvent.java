package com.algaworks.algafood.core.events;

import com.algaworks.algafood.domain.entitys.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

    private Pedido pedido;
}
