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

import com.algaworks.algafood.api.domain_to_DTO.RestauranteModel;
import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.api.input_model_to_domain.RestauranteInputModelToRestauranteDomainModel;
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

	@Autowired
	private RestauranteModel restauranteToDTO;

	@Autowired
	RestauranteInputModelToRestauranteDomainModel inputToDomain;
	
	
	//------CONTROLLER_LISTAR_RESTAURANTES-------//
	@GetMapping
	public List<RestauranteModel> listar(){
		return restauranteToDTO.toListRestauranteDTOs(restauranteRepository.findAll());
	}
	
	
	//------CONTROLLER_BUSCAR_RESTAURANTES------//
	@GetMapping("/{id}")
	public RestauranteModel buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.serviceBuscar(id);
		return restauranteToDTO.toRestauranteDTO(restaurante);
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_NOME------//
	@GetMapping("/porNome")
	public List<RestauranteModel> buscarPorNome(String nome){
		return restauranteToDTO.toListRestauranteDTOs(restauranteRepository.findRestauranteByNomeContaining(nome));
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_TAXA------//
	@GetMapping("/listar")
	public List<RestauranteModel> ListrPorTaxa(String nome, Double tx_inicial, Double tx_final){
		return restauranteToDTO.toListRestauranteDTOs(restauranteRepository.buscarPorTaxa(nome, tx_inicial, tx_final));
	}
	
	
	//------CONTROLLER_ADICIONAR_RESTAURANTES------//
	@PostMapping
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			Restaurante restaurante = inputToDomain.toRestauranteModel(restauranteInputModel);
			return restauranteToDTO.toRestauranteDTO(restauranteService.serviceAdicionar(restaurante));
			
		}catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	//------CONTROLLER_ATUALIZAR_RSTAURANTES------//
	@PutMapping("/{id}")
	public RestauranteModel atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			return restauranteToDTO.toRestauranteDTO(restauranteService.serviceAtualizar(id, restauranteInputModel));

		}catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
