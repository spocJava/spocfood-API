package com.algaworks.algafood.api.domain_to_DTO;

import com.algaworks.algafood.api.DTO.ProdutoDTO;
import com.algaworks.algafood.domain.entitys.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModel {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDTO toProdutoDTO(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toListProdutoDTO(List<Produto> produtos){
        return produtos.stream()
                .map(produto -> toProdutoDTO(produto))
                .collect(Collectors.toList());
    }
}
