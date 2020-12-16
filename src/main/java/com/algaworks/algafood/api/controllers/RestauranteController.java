package com.algaworks.algafood.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.Restaurante;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;
import com.algaworks.algafood.domain.services.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	//------CONTROLLER_LISTAR_RESTAURANTES-------//
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES------//
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
		if(restauranteOptional.isPresent()) {
			return ResponseEntity.ok(restauranteOptional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_NOME------//
	@GetMapping("/porNome")
	public List<Restaurante> buscarPorNome(String nome){
		return restauranteRepository.findRestauranteByNomeContaining(nome);
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_TAXA------//
	@GetMapping("/listar")
	public List<Restaurante> ListrPorTaxa(String nome, Double tx_inicial, Double tx_final){
		return restauranteRepository.buscarPorTaxa(nome, tx_inicial, tx_final);
	}
	
	//------CONTROLLER_ADICIONAR_RESTAURANTES------//
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestauranteService.adicionar(restaurante);
			return ResponseEntity.ok(restaurante);
			
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}
	
	//------CONTROLLER_ATUALIZAR_RSTAURANTES------//
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		try {
			Optional<Restaurante> restAtualOptional = restauranteRepository.findById(id);
			
			if(restAtualOptional.isPresent()) {
				BeanUtils.copyProperties(restaurante, restAtualOptional.get(), "id", "formasPagamento", "endereco",
						"dataCriacao", "produtos");
				Restaurante restAtual = cadastroRestauranteService.adicionar(restAtualOptional.get());
				return ResponseEntity.ok(restAtual);
			}
			
			return ResponseEntity.notFound().build();
			
			
		}catch(EntidadeNaoEncontradaExecption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
	}

}
