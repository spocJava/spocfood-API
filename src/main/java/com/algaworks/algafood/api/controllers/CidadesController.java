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
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exeptions.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.Cidade;
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
	public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
		Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);
		
		if(cidadeOptional.isPresent()) {
			return ResponseEntity.ok(cidadeOptional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//------CONTROLLER_ADICIONAR_CIDADES------//
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		try {
			cadastroCidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
			
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	//------CONTROLLER_ATUALIZAR_CIDADES------//
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
		try{
			Optional<Cidade> cidadeAtualOptional = cadastroCidadeService.buscar(id);
			
			if(cidadeAtualOptional.isPresent()) {
				BeanUtils.copyProperties(cidade, cidadeAtualOptional.get(), "id");
				Cidade cidadeAtual = cadastroCidadeService.salvar(cidadeAtualOptional.get());
				return ResponseEntity.ok(cidadeAtual);
			}
			
			return ResponseEntity.notFound().build();
				
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			cadastroCidadeService.remover(id);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoExeption e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
