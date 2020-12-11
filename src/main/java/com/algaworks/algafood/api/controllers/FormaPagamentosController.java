package com.algaworks.algafood.api.controllers;

import java.util.List;
import java.util.Optional;

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

import com.algaworks.algafood.domain.exeptions.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.FormaPagamento;
import com.algaworks.algafood.domain.repositorys.FormaPagamentoRepository;
import com.algaworks.algafood.domain.services.FormaPagamentoService;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentosController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	//-----CONTROLLER_LISTAR_FORMAS_DE_PAGAMENTOS------//
	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoRepository.findAll();
	}
	
	//-----CONTROLLER_BUSCAR_FORMA_DE_PAGAMENTO------//
	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long id){
		
		Optional<FormaPagamento> formaPagamentoOptional = formaPagamentoRepository.findById(id);
		
		if(formaPagamentoOptional.isPresent()) {
			return ResponseEntity.ok(formaPagamentoOptional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//-----CONTROLLER_ADICIONAR_FORMAS_DE_PAGAMNETO------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento){
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	//-----CONTROLLER_ATUALIZAR_FORMAS_DE_PAGAMNETO------//
	@PutMapping("/{id}")
	public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long id, 
			@RequestBody FormaPagamento formaPagamento){
		Optional<FormaPagamento> formaPagamentoAtualOptional = formaPagamentoRepository.findById(id);
		
		if(formaPagamentoAtualOptional.isPresent()) {
			
			BeanUtils.copyProperties(formaPagamento, formaPagamentoAtualOptional.get(), "id");
			FormaPagamento formaPag = formaPagamentoRepository.save(formaPagamentoAtualOptional.get());
			return ResponseEntity.ok(formaPag);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//-----CONTROLLER_REMOVER_FORMAS_DE_PAGAMNETO------//
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			formaPagamentoService.excluir(id);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoExeption e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
}