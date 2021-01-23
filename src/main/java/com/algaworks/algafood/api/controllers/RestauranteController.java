package com.algaworks.algafood.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;
import com.algaworks.algafood.domain.services.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService restauranteService;
	
	
	//------CONTROLLER_LISTAR_RESTAURANTES-------//
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	
	//------CONTROLLER_BUSCAR_RESTAURANTES------//
	@GetMapping("/{id}")
	public Restaurante buscar(@PathVariable Long id) {
		return restauranteService.serviceBuscar(id);
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
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return restauranteService.serviceAdicionar(restaurante);
			
		}catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	//------CONTROLLER_ATUALIZAR_RSTAURANTES------//
	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante) {
		try {
			return restauranteService.serviceAtualizar(id, restaurante);
			
		}catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
