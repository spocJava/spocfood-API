package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.entitys.Pedido;
import com.algaworks.algafood.domain.repositorys.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FluxoPedidoService {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    @Transactional
    public void setStatusToConfirmed(String codigo){
        Pedido pedido = pedidoService.getPedido(codigo);
        pedido.changeToConfirmed();
        pedidoRepository.save(pedido);
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
        pedidoRepository.save(pedido);
    }
}
