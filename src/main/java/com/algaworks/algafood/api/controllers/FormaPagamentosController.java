package com.algaworks.algafood.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.algaworks.algafood.api.DTO.FormaPagamentoDTO;
import com.algaworks.algafood.api.domain_to_DTO.FormaPagamentoModel;
import com.algaworks.algafood.api.input_model.FormaPagamentoInputModel;
import com.algaworks.algafood.api.input_model_to_domain.FormaPagamentoInputModelToDomainModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.entitys.FormaPagamento;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.FormaPagamentoRepository;
import com.algaworks.algafood.domain.services.FormaPagamentoService;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentosController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	FormaPagamentoModel formaPagamentoModel;

	@Autowired
	FormaPagamentoInputModelToDomainModel formaPagamentoInputModelToDomainModel;
	
	//-----CONTROLLER_LISTAR_FORMAS_DE_PAGAMENTOS------//
	@GetMapping
	public List<FormaPagamentoDTO> listar(){
		return formaPagamentoModel.toListFormaPagametoDTO(formaPagamentoRepository.findAll());
	}
	
	//-----CONTROLLER_BUSCAR_FORMA_DE_PAGAMENTO------//
	@GetMapping("/{id}")
	public FormaPagamentoDTO buscar(@PathVariable Long id){
		return formaPagamentoModel.toFormaPagametoDTO(formaPagamentoService.getFormaPagamento(id));
	}
	
	//-----CONTROLLER_ADICIONAR_FORMAS_DE_PAGAMNETO------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInputModel formaPagamentoInputModel){
		FormaPagamento formaPagamento = formaPagamentoInputModelToDomainModel.toFormaPagamentoDomainModel(formaPagamentoInputModel);
		return formaPagamentoModel.toFormaPagametoDTO(formaPagamentoService.addFormaPagamento(formaPagamento));
	}
	
	//-----CONTROLLER_ATUALIZAR_FORMAS_DE_PAGAMNETO------//
	@PutMapping("/{id}")
	public FormaPagamentoDTO atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInputModel formaPagamentoInputModel){
		return formaPagamentoModel.toFormaPagametoDTO(formaPagamentoService.upDateFormaPagamento(id, formaPagamentoInputModel));
	}
	
	//-----CONTROLLER_REMOVER_FORMAS_DE_PAGAMNETO------//
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		formaPagamentoService.excluir(id);
	}
	
}