package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.FormaPagamentoDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.FormaPagamentoModel;
import com.algaworks.algafood.api.input_model.FormaPagamentoInputModel;
import com.algaworks.algafood.api.input_model.input_model_to_domain.FormaPagamentoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.FormaPagamento;
import com.algaworks.algafood.domain.repositorys.FormaPagamentoRepository;
import com.algaworks.algafood.domain.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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