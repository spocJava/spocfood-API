package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.FormaPagamentoDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.FormaPagamentoModel;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.services.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private CadastroRestauranteService restauranteService;
	@Autowired
	FormaPagamentoModel formaPagamentoModel;

	//------CONTROLLER_LISTAR_FORMA_PAGAMENTO_DO_RESTAURANTE-------//
	@GetMapping
	public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = restauranteService.serviceBuscar(restauranteId);
		return formaPagamentoModel.toListFormaPagametoDTO(restaurante.getFormasPagamento());
	}

	//------ASSOCIAR_NOVA_FORMA_PAGAMENTO_AO_RESTAURANTE-------//
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
		restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

	//------DESASSOCIAR_FORMA_PAGAMENTO_DO_RESTAURANTE-------//
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}

}
