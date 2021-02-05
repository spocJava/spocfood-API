package com.algaworks.algafood.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.DTO.CidadeDTO;
import com.algaworks.algafood.api.domain_to_DTO.CidadeModel;
import com.algaworks.algafood.api.input_model.CidadeInputModel;
import com.algaworks.algafood.api.input_model_to_domain.CidadeInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Cidade;
import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.repositorys.CidadeRepository;
import com.algaworks.algafood.domain.services.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	CidadeModel cidadeModel;

	@Autowired
	CidadeInputModelToDomainModel cidadeToDomainModel;

	
	//------CONTROLLER_LISTAR_CIDADES------//
	@GetMapping
	public List<CidadeDTO> listar(){
		return cidadeModel.toListCidadeDTO(cidadeRepository.findAll());
	}
	
	
	//------CONTROLLER_LISTAR_CIDADES_POR_NOME-------//
	@GetMapping("/porNome")
	public List<CidadeDTO> buscarPorNome(String nome) {
		return cidadeModel.toListCidadeDTO(cidadeRepository.findByNomeContaining(nome));
	}
	
	
	//------CONTROLLER_BUSCAR_CIDADES------//
	@GetMapping("/{id}")
	public CidadeDTO buscar(@PathVariable Long id) {
		return cidadeModel.toCidadeDTO(cadastroCidadeService.buscar(id));
	}
	
	
	//------CONTROLLER_ADICIONAR_CIDADES------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody @Valid CidadeInputModel cidadeInputModel) {
		try {
			Cidade cidade = cidadeToDomainModel.convertToCidadeDomainModel(cidadeInputModel);
			return cidadeModel.toCidadeDTO(cadastroCidadeService.salvar(cidade));
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	//------CONTROLLER_ATUALIZAR_CIDADES------//
	@PutMapping("/{id}")
	public CidadeDTO atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInputModel cidadeInputModel){
		Cidade cidadeAtual = cadastroCidadeService.buscar(id);
		cidadeToDomainModel.copyPropertiesToDomainModel(cidadeInputModel, cidadeAtual);
		try {
			return cidadeModel.toCidadeDTO(cadastroCidadeService.salvar(cidadeAtual));
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}		
	
	
	//------CONTROLLER_REMOVER_CIDADES------//
	@DeleteMapping("/{id}") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCidadeService.remover(id);		
	}
}
