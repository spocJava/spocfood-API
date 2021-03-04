package com.algaworks.algafood.api.input_model_to_domain;

import com.algaworks.algafood.api.input_model.PedidoInputModel;
import com.algaworks.algafood.domain.entitys.Pedido;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoInputModelToDomainModel {

    private final ModelMapper modelMapper;

    public Pedido toDomainPedido(PedidoInputModel pedidoInputModel){
        return modelMapper.map(pedidoInputModel, Pedido.class);
    }

    public void copyProperties(PedidoInputModel inputModel, Pedido pedido){
        modelMapper.map(inputModel, pedido);
    }
}
