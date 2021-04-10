package com.algaworks.algafood.api.DTO.domain_to_DTO;

import com.algaworks.algafood.api.DTO.PedidoResumoDTO;
import com.algaworks.algafood.domain.entitys.Pedido;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PedidoResumoModel {

    private final ModelMapper modelMapper;

    public PedidoResumoDTO toPedidoDTO(Pedido pedido){
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    public List<PedidoResumoDTO> toListPedidoDTO(List<Pedido> pedidos){
        return pedidos.stream()
                .map(pedido -> toPedidoDTO(pedido))
                .collect(Collectors.toList());
    }
}
