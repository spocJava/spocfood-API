package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.input_model.RestauranteInputModel;
import com.algaworks.algafood.api.input_model.input_model_to_domain.RestauranteInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.*;
import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.RestauranteNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CadastroRestauranteService {


	private final RestauranteRepository restauranteRepository;
	private final CadrastroCozinhaService cozinhaService;
	private final CadastroCidadeService cidadeService;
	private final RestauranteInputModelToDomainModel restInputToModel;
	private final FormaPagamentoService formaPagamentoService;
	private final UsuarioService usuarioService;
	
	
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

	//--------- ATIVAR VÁRIOS RESTAURANTE ----------//
	@Transactional
	public void ativarGrupoRest(List<Long> restauranteIds){
		try{
			restauranteIds.forEach(this::ativar);
		}catch(RestauranteNaoEncontradaException ex){
			throw new NegocioException(ex.getMessage(), ex);
		}
	}

	//--------- INATIVAR VÁRIOS RESTAURANTE ----------//
	@Transactional
	public void inativarGrupoRest(List<Long> restauranteIds){
		try{
			restauranteIds.forEach(this::inativar);
		}catch(RestauranteNaoEncontradaException ex){
			throw new NegocioException(ex.getMessage(), ex);
		}

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
	@Transactional
	public Restaurante serviceAtualizar(Long id, RestauranteInputModel restInput) {
		Restaurante restauranteAtual = serviceBuscar(id);
        restInputToModel.copyInputToModel(restInput, restauranteAtual);
		return serviceAdicionar(restauranteAtual);
	}

	//---------SERVICE_FECHAR_RESTAURANTE----------//
	@Transactional
	public void fecharRestaurante(Long restauranteId){
		Restaurante restaurante = serviceBuscar(restauranteId);
		restaurante.fecharRestaurante();
	}

	//---------SERVICE_ABRIR_RESTAURANTE----------//
	@Transactional
	public void abrirRestaurante(Long restauranteId){
		Restaurante restaurante = serviceBuscar(restauranteId);
		restaurante.abrirRestaurante();
	}

	//---------ADICIONA_RESPONSAVEL_PELO_RESTAURANTE----------//
	@Transactional
	public void addResponsavel(Long restauranteId, Long usuarioId){
		Restaurante restaurante = serviceBuscar(restauranteId);
		Usuario usuario = usuarioService.getUserById(usuarioId);
		restaurante.addResponsavel(usuario);
	}


	//---------ADICIONA_RESPONSAVEL_PELO_RESTAURANTE----------//
	@Transactional
	public void removerResponsavel(Long restauranteId, Long usuarioId){
		Restaurante restaurante = serviceBuscar(restauranteId);
		Usuario usuario = usuarioService.getUserById(usuarioId);
		restaurante.removerResponsavel(usuario);
	}
}
