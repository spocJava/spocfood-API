package com.algaworks.algafood.api.domain_to_DTO;

import com.algaworks.algafood.api.DTO.FormaPagamentoDTO;
import com.algaworks.algafood.domain.entitys.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoModel {

    @Autowired
    ModelMapper modelMapper;

    public FormaPagamentoDTO toFormaPagametoDTO(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    public List<FormaPagamentoDTO> toListFormaPagametoDTO(Collection<FormaPagamento> formaPagamentos){
        return formaPagamentos.stream()
                .map(pagamentos -> toFormaPagametoDTO(pagamentos))
                .collect(Collectors.toList());
    }
}
