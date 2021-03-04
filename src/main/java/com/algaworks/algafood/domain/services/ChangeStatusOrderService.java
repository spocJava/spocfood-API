package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.entitys.Pedido;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ChangeStatusOrderService {

    private final PedidoService pedidoService;

    @Transactional
    public void setStatusToConfirmed(String codigo){
        Pedido pedido = pedidoService.getPedido(codigo);
        pedido.changeToConfirmed();
    }

    @Transactional
    public void setStatusToDelivered(String codigo){
        Pedido pedido = pedidoService.getPedido(codigo);
        pedido.changeToDelivered();
    }

    @Transactional
    public void setStatusToCanceled(String codigo){
        Pedido pedido = pedidoService.getPedido(codigo);
        pedido.changeToCanceled();
    }
}
