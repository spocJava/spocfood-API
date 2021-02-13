package com.algaworks.algafood.api.controllers;

import java.util.List;
import javax.validation.Valid;

import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.CidadeNaoEncontradaException;
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

import com.algaworks.algafood.api.DTO.RestauranteDTO;
import com.algaworks.algafood.api.domain_to_DTO.RestauranteModel;
import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.api.input_model_to_domain.RestauranteInputModelToDomainModel;
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
	private RestauranteModel restauranteModel;

	@Autowired
	RestauranteInputModelToDomainModel inputToDomain;
	
	
	//------CONTROLLER_LISTAR_RESTAURANTES-------//
	@GetMapping
	public List<RestauranteDTO> listar(){
		return restauranteModel.toListRestauranteDTOs(restauranteRepository.findAll());
	}
	
	
	//------CONTROLLER_BUSCAR_RESTAURANTES------//
	@GetMapping("/{id}")
	public RestauranteDTO buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.serviceBuscar(id);
		return restauranteModel.toRestauranteDTO(restaurante);
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_NOME------//
	@GetMapping("/porNome")
	public List<RestauranteDTO> buscarPorNome(String nome){
		return restauranteModel.toListRestauranteDTOs(restauranteRepository.findRestauranteByNomeContaining(nome));
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_TAXA------//
	@GetMapping("/listar")
	public List<RestauranteDTO> ListrPorTaxa(String nome, Double tx_inicial, Double tx_final){
		return restauranteModel.toListRestauranteDTOs(restauranteRepository.buscarPorTaxa(nome, tx_inicial, tx_final));
	}
	
	//------CONTROLLER_ADICIONAR_RESTAURANTES------//
	@PostMapping
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			Restaurante restaurante = inputToDomain.toRestauranteDamainModel(restauranteInputModel);
			return restauranteModel.toRestauranteDTO(restauranteService.serviceAdicionar(restaurante));
			
		}catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	//------CONTROLLER_ATUALIZAR_RSTAURANTES------//
	@PutMapping("/{id}")
	public RestauranteDTO atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			return restauranteModel.toRestauranteDTO(restauranteService.serviceAtualizar(id, restauranteInputModel));

		}catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	//- Ativa o restaurante
	@PutMapping("/{restauranteId}/ativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)  // Com o PUT no recurso restaurantes/restauranteId/ativar você ativa um restaurante
	public void ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
	}

	//- Inativa o restaurante
	@DeleteMapping("/{restauranteId}/ativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)  // Com o DELETE no recurso restaurantes/restauranteId/ativar você inativa um restaurante
	public void inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
	}
}
