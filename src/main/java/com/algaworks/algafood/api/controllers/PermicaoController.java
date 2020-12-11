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
import com.algaworks.algafood.domain.models.Permicao;
import com.algaworks.algafood.domain.repositorys.PermicaoRepository;
import com.algaworks.algafood.domain.services.PermicaoService;

@RestController
@RequestMapping("/permicões")
public class PermicaoController {
	
	@Autowired
	private PermicaoRepository permicaoRepository;
	
	@Autowired
	private PermicaoService permicaoService;

	//-----CONTROLLER_LISTAR_PERMIÇÕES------//
	@GetMapping
	public List<Permicao> listar(){
		return permicaoRepository.findAll();
	}
	
	//-----CONTROLLER_BUSCAR_PERMIÇÕES------//
	@GetMapping("/{id}")
	public ResponseEntity<Permicao> buscar(@PathVariable Long id) {
		Optional<Permicao> permicaoOptional = permicaoRepository.findById(id);
		
		if(permicaoOptional.isPresent()) {
			return ResponseEntity.ok(permicaoOptional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//-----CONTROLLER_ADICIONAR_PERMIÇÕES------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Permicao adicionar(@RequestBody Permicao permicao) {
		return permicaoRepository.save(permicao);
	}
	
	//-----CONTROLLER_ATUALIZAR_PERMIÇÕES------//
	@PutMapping("/{id}")
	public ResponseEntity<Permicao> atualizar(@PathVariable Long id, @RequestBody Permicao permicao){
		Optional<Permicao> permicaoAtualOptional = permicaoRepository.findById(id);
		
		if(permicaoAtualOptional.isPresent()) {
			
			BeanUtils.copyProperties(permicao, permicaoAtualOptional.get(), "id");
			Permicao permicaoModificada = permicaoRepository.save(permicaoAtualOptional.get());
			return ResponseEntity.ok(permicaoModificada);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//-----CONTROLLER_REMOVER_PERMIÇÕES------//
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			permicaoService.excluir(id);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoExeption e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}	
}
