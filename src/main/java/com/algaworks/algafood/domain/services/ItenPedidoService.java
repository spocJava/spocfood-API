package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.entitys.ItemPedido;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.ItemPedidoNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.ItemPedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItenPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> getAllItensPedido(){
        return itemPedidoRepository.findAll();
    }

    public ItemPedido getItemPedido(Long id){
        return itemPedidoRepository.findById(id).orElseThrow(() -> new ItemPedidoNaoEncontradaException(id));
    }
}
