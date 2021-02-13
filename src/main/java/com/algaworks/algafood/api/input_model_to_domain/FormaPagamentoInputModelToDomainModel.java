package com.algaworks.algafood.api.input_model_to_domain;

import com.algaworks.algafood.api.input_model.FormaPagamentoInputModel;
import com.algaworks.algafood.domain.entitys.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputModelToDomainModel {

    @Autowired
    ModelMapper modelMapper;

    public FormaPagamento toFormaPagamentoDomainModel(FormaPagamentoInputModel formaPagamentoInputModel){
        return modelMapper.map(formaPagamentoInputModel, FormaPagamento.class);
    }

    public void copyProperties(FormaPagamentoInputModel formaPagamentoInputModel, FormaPagamento formaPagamento){

        modelMapper.map(formaPagamentoInputModel, formaPagamento);
    }
}
