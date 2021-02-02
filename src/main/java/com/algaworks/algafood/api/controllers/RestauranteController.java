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

import com.algaworks.algafood.api.domainmodel_to_modelDTO.RestauranteToDTO;
import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.api.model_DTO.RestauranteDTO;
import com.algaworks.algafood.domain.entitys.Cozinha;
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
	private RestauranteToDTO restauranteToDTO;
	
	
	//------CONTROLLER_LISTAR_RESTAURANTES-------//
	@GetMapping
	public List<RestauranteDTO> listar(){
		return restauranteToDTO.toListRestauranteDTOs(restauranteRepository.findAll());
	}
	
	
	//------CONTROLLER_BUSCAR_RESTAURANTES------//
	@GetMapping("/{id}")
	public RestauranteDTO buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.serviceBuscar(id);
		return restauranteToDTO.toRestauranteDTO(restaurante);
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_NOME------//
	@GetMapping("/porNome")
	public List<RestauranteDTO> buscarPorNome(String nome){
		return restauranteToDTO.toListRestauranteDTOs(restauranteRepository.findRestauranteByNomeContaining(nome));
	}
	
	//------CONTROLLER_BUSCAR_RESTAURANTES_POR_TAXA------//
	@GetMapping("/listar")
	public List<RestauranteDTO> ListrPorTaxa(String nome, Double tx_inicial, Double tx_final){
		return restauranteToDTO.toListRestauranteDTOs(restauranteRepository.buscarPorTaxa(nome, tx_inicial, tx_final));
	}
	
	
	//------CONTROLLER_ADICIONAR_RESTAURANTES------//
	@PostMapping
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			Restaurante restaurante = toRestauranteModel(restauranteInputModel);
			return restauranteToDTO.toRestauranteDTO(restauranteService.serviceAdicionar(restaurante));
			
		}catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	//------CONTROLLER_ATUALIZAR_RSTAURANTES------//
	@PutMapping("/{id}")
	public RestauranteDTO atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			Restaurante restaurante = toRestauranteModel(restauranteInputModel);
			return restauranteToDTO.toRestauranteDTO(restauranteService.serviceAtualizar(id, restaurante));
			
		}catch(CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	//--converte restauranteInputModel em Restaurante do dominio
	private Restaurante toRestauranteModel(RestauranteInputModel restauranteInputModel){
		Restaurante restaurante = new Restaurante();

		restaurante.setNome(restauranteInputModel.getNome());
		restaurante.setTaxaFrete(restauranteInputModel.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInputModel.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return restaurante;
	}

}
