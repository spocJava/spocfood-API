package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.api.input_model_to_domain.RestauranteInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Cidade;
import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.entitys.FormaPagamento;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.RestauranteNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CadrastroCozinhaService cozinhaService;
	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	RestauranteInputModelToDomainModel restInputToModel;
	@Autowired
	FormaPagamentoService formaPagamentoService;
	
	
	//---------SERVICE_ADICIONAR_RESTAURANTES----------//
	@Transactional
	public Restaurante serviceAdicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaService.serviceBuscar(cozinhaId);
		Cidade cidade = cidadeService.buscar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);
	}

	//--------- ATIVAR RESTAURANTE ----------//
	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restaurante = serviceBuscar(restauranteId);
		restaurante.ativar();
	}

	//--------- DESATIVAR RESTAURANTE ----------//
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restaurante = serviceBuscar(restauranteId);
		restaurante.inativar();
	}

	//--------- ASSOCIAR NOVA FORMA DE PAGAMENTO ----------//
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
		Restaurante restaurante = serviceBuscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.getFormaPagamento(formaPagamentoId);
		restaurante.associarFormaPagamento(formaPagamento);
	}

	//--------- DESASSOCIAR FORMA DE PAGAMENTO ----------//
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
		Restaurante restaurante = serviceBuscar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.getFormaPagamento(formaPagamentoId);
		restaurante.desassociarFormaPagamento(formaPagamento);
	}
	
	//---------SERVICE_BUSCAR_RESTAURANTES----------//
	public Restaurante serviceBuscar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradaException(id));
	}
	
	
	//---------SERVICE_ATUALIZAR_RESTAURANTES----------//
	public Restaurante serviceAtualizar(Long id, RestauranteInputModel restInput) {
		Restaurante restauranteAtual = serviceBuscar(id);
        restInputToModel.copyInputToModel(restInput, restauranteAtual);
		return serviceAdicionar(restauranteAtual);
	}
}
