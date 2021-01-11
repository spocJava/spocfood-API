package com.algaworks.algafood.api.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

	
	//------CONTROLLER_LISTAR_CIDADES------//
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	
	//------CONTROLLER_LISTAR_CIDADES_POR_NOME-------//
	@GetMapping("/porNome")
	public List<Cidade> buscarPorNome(String nome) {
		return cidadeRepository.findByNomeContaining(nome);
	}
	
	
	//------CONTROLLER_BUSCAR_CIDADES------//
	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id) {
		return cadastroCidadeService.buscar(id);
	}
	
	
	//------CONTROLLER_ADICIONAR_CIDADES------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {
			return cadastroCidadeService.salvar(cidade);
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	//------CONTROLLER_ATUALIZAR_CIDADES------//
	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
		Cidade cidadeAtual = cadastroCidadeService.buscar(id);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			try {
				return cadastroCidadeService.salvar(cidadeAtual);
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
