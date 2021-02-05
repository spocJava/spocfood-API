package com.algaworks.algafood.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.algaworks.algafood.api.DTO.EstadoDTO;
import com.algaworks.algafood.api.domain_to_DTO.EstadoModel;
import com.algaworks.algafood.domain.entitys.Estado;
import com.algaworks.algafood.domain.repositorys.EstadoRepository;
import com.algaworks.algafood.domain.services.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@Autowired
	EstadoModel estadoToDTO;
	
	//-----CONTROLLER_LISTAR_ESTADOS------//
	@GetMapping
	public List<EstadoDTO> listar() {
		return estadoToDTO.toListEstadoDTO(estadoRepository.findAll());
	}
	
	
	//-----CONTROLLER_BUSCAR_ESTADOS------//
	@GetMapping("/{id}")
	public EstadoDTO buscar(@PathVariable Long id) {
		return estadoToDTO.toEstadoDTO(cadastroEstadoService.buscar(id));
	}
	
	
	//-----CONTROLLER_LISTAR_ESTADOS_POR_NOME------//
	@GetMapping("/porNome")
	public List<EstadoDTO> buscarPorNome(String nome){
		return estadoToDTO.toListEstadoDTO(estadoRepository.findEstadoByNomeContaining(nome));
	}
	
	
	//-----CONTROLLER_ADICIONAR_ESTADOS------//
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoDTO adicionar(@RequestBody @Valid Estado estado){
		return estadoToDTO.toEstadoDTO(cadastroEstadoService.salvar(estado));
	}
	
	
	//-----CONTROLLER_ATUALIZAR_ESTADOS------//
	@PutMapping("/{id}")
	public EstadoDTO atualizar(@PathVariable Long id, @RequestBody @Valid Estado estado) {
		Estado estadoAtual = cadastroEstadoService.buscar(id);
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoToDTO.toEstadoDTO(cadastroEstadoService.salvar(estadoAtual));
	}
		
	//-----CONTROLLER_REMOVER_ESTADOS------//
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		cadastroEstadoService.remover(id);
	}
	
}
