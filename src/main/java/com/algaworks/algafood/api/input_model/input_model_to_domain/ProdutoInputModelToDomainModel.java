package com.algaworks.algafood.api.input_model.input_model_to_domain;

import com.algaworks.algafood.api.input_model.ProdutoInputModel;
import com.algaworks.algafood.domain.entitys.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputModelToDomainModel {

    @Autowired
    private ModelMapper modelMapper;

    public Produto inputToDomail(ProdutoInputModel produtoInputModel){
        return modelMapper.map(produtoInputModel, Produto.class);
    }

    public void copyProperties(ProdutoInputModel input, Produto produto){
        modelMapper.map(input, produto);
    }
}
