package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.EstadoDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.EstadoModel;
import com.algaworks.algafood.api.input_model.EstadoInputModel;
import com.algaworks.algafood.api.input_model.input_model_to_domain.EstadoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Estado;
import com.algaworks.algafood.domain.repositorys.EstadoRepository;
import com.algaworks.algafood.domain.services.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@Autowired
	EstadoModel estadoModel;

	@Autowired
	EstadoInputModelToDomainModel estadoInputModelToDomainModel;
	
	//-----CONTROLLER_LISTAR_ESTADOS------//
	@GetMapping
	public List<EstadoDTO> listar() {
		return estadoModel.toListEstadoDTO(estadoRepository.findAll());
	}
	
	
	//-----CONTROLLER_BUSCAR_ESTADOS------//
	@GetMapping("/{id}")
	public EstadoDTO buscar(@PathVariable Long id) {
		return estadoModel.toEstadoDTO(cadastroEstadoService.buscar(id));
	}
	
	
	//-----CONTROLLER_LISTAR_ESTADOS_POR_NOME------//
	@GetMapping("/porNome")
	public List<EstadoDTO> buscarPorNome(String nome){
		return estadoModel.toListEstadoDTO(estadoRepository.findEstadoByNomeContaining(nome));
	}
	
	
	//-----CONTROLLER_ADICIONAR_ESTADOS------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoDTO adicionar(@RequestBody @Valid EstadoInputModel estadoInputModel){
		Estado estado = estadoInputModelToDomainModel.toEstadoDomainModel(estadoInputModel);
		return estadoModel.toEstadoDTO(cadastroEstadoService.salvar(estado));
	}
	
	
	//-----CONTROLLER_ATUALIZAR_ESTADOS------//
	@PutMapping("/{id}")
	public EstadoDTO atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInputModel estadoInputModel) {
		Estado estadoAtual = cadastroEstadoService.buscar(id);
	    estadoInputModelToDomainModel.copyPropertiesToDomainModel(estadoInputModel, estadoAtual);
		return estadoModel.toEstadoDTO(cadastroEstadoService.salvar(estadoAtual));
	}
		
	//-----CONTROLLER_REMOVER_ESTADOS------//
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		cadastroEstadoService.remover(id);
	}
	
}
