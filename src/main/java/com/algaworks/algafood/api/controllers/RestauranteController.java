package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.RestauranteDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.RestauranteModel;
import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.api.input_model.input_model_to_domain.RestauranteInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.entitys.view.RestauranteView;
import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;
import com.algaworks.algafood.domain.services.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@AllArgsConstructor
public class RestauranteController {

	private final RestauranteRepository restauranteRepository;
	private final CadastroRestauranteService restauranteService;
	private final RestauranteModel restauranteModel;
	private final RestauranteInputModelToDomainModel inputToDomain;
	
	

	@GetMapping
	@JsonView(RestauranteView.ResumoView.class)
	public List<RestauranteDTO> listar(){
		return restauranteModel.toListRestauranteDTOs(restauranteRepository.findAll());
	}

	
	@GetMapping("/{id}")
	public RestauranteDTO buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.serviceBuscar(id);
		return restauranteModel.toRestauranteDTO(restaurante);
	}
	

	@GetMapping("/porNome")
	public List<RestauranteDTO> buscarPorNome(String nome){
		return restauranteModel.toListRestauranteDTOs(restauranteRepository.findRestauranteByNomeContaining(nome));
	}
	
	
	@GetMapping("/listar")
	public List<RestauranteDTO> ListrPorTaxa(String nome, Double tx_inicial, Double tx_final){
		return restauranteModel.toListRestauranteDTOs(restauranteRepository.buscarPorTaxa(nome, tx_inicial, tx_final));
	}
	
	
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			Restaurante restaurante = inputToDomain.toRestauranteDamainModel(restauranteInputModel);
			return restauranteModel.toRestauranteDTO(restauranteService.serviceAdicionar(restaurante));
			
		}catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	
	@PutMapping("/{id}")
	public RestauranteDTO atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInputModel restauranteInputModel) {
		try {
			return restauranteModel.toRestauranteDTO(restauranteService.serviceAtualizar(id, restauranteInputModel));

		}catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	
	@PutMapping("/{restauranteId}/ativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)  
	public void ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
	}

	
	@DeleteMapping("/{restauranteId}/ativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)  
	public void inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
	}


	@PutMapping("/ativacao-multipla")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplosRest(@RequestBody List<Long> restauranteId){
		restauranteService.ativarGrupoRest(restauranteId);
	}

	
	@DeleteMapping("/ativacao-multipla")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplosRest(@RequestBody List<Long> restauranteId){
		restauranteService.inativarGrupoRest(restauranteId);
	}

	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrirRestaurante(@PathVariable  Long restauranteId){
		restauranteService.abrirRestaurante(restauranteId);
	}

	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fecharRestaurante(@PathVariable Long restauranteId){
		restauranteService.fecharRestaurante(restauranteId);
	}
}
