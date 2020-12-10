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
import com.algaworks.algafood.domain.models.Estado;
import com.algaworks.algafood.domain.repositorys.EstadoRepository;
import com.algaworks.algafood.domain.services.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	//-----CONTROLLER_LISTAR_ESTADOS------//
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	//-----CONTROLLER_BUSCAR_ESTADOS------//
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {
		Optional<Estado> estadoOptinal = estadoRepository.findById(id);
		
		if(estadoOptinal.isPresent()) {
			return ResponseEntity.ok(estadoOptinal.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/porNome")
	public List<Estado> buscarPorNome(String nome){
		return estadoRepository.findEstadoByNomeContaining(nome);
	}
	
	//-----CONTROLLER_ADICIONAR_ESTADOS------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado){
		return cadastroEstadoService.salvar(estado);
	}
	
	//-----CONTROLLER_ATUALIZAR_ESTADOS------//
	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
		Optional<Estado> estadoAtualOptional = cadastroEstadoService.bucar(id);
		
		if(estadoAtualOptional.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtualOptional.get(), "id");
			Estado estadoAtual = cadastroEstadoService.salvar(estadoAtualOptional.get());
			return ResponseEntity.ok(estadoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			cadastroEstadoService.remover(id);
			return ResponseEntity.noContent().build();
			
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.notFound().build();
			
		}catch(EntidadeEmUsoExeption e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
	}
	
}
