package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.input_model.FormaPagamentoInputModel;
import com.algaworks.algafood.api.input_model.input_model_to_domain.FormaPagamentoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.FormaPagamento;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.FormaPagamentoInUseException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.FormaPagamentoNotFoundException;
import com.algaworks.algafood.domain.repositorys.FormaPagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	FormaPagamentoInputModelToDomainModel formaPagamentoInputModelToDomainModel;

	@Autowired
	ModelMapper modelMapper;

	public List<FormaPagamento> getAllFormaPagamentos(){
		return formaPagamentoRepository.findAll();
	}

	public FormaPagamento getFormaPagamento(Long id){
		return formaPagamentoRepository.findById(id).orElseThrow(() -> new FormaPagamentoNotFoundException(id));
	}

	public FormaPagamento addFormaPagamento(FormaPagamento formaPagamento){
		return formaPagamentoRepository.save(formaPagamento);
	}

	@Transactional
	public FormaPagamento upDateFormaPagamento(Long id, FormaPagamentoInputModel formaPagamentoInputModel){
		FormaPagamento formaPagamentoAtual =  getFormaPagamento(id);
		formaPagamentoInputModelToDomainModel.copyProperties(formaPagamentoInputModel, formaPagamentoAtual);
		return addFormaPagamento(formaPagamentoAtual);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
			formaPagamentoRepository.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new FormaPagamentoNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new FormaPagamentoInUseException(id);
		}
	}
}
