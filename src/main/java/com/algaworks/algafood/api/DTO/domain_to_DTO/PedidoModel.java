package com.algaworks.algafood.api.DTO.domain_to_DTO;

import com.algaworks.algafood.api.DTO.PedidoDTO;
import com.algaworks.algafood.domain.entitys.Pedido;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PedidoModel {

    private final ModelMapper modelMapper;

    public PedidoDTO toPedidoDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public List<PedidoDTO> toListPedidoDTO(List<Pedido> pedidos){
        return pedidos.stream()
                .map(pedido -> toPedidoDTO(pedido))
                .collect(Collectors.toList());
    }
}
