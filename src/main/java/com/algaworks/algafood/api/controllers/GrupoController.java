package com.algaworks.algafood.api.controllers;

import java.util.List;

import com.algaworks.algafood.api.DTO.GrupoDTO;
import com.algaworks.algafood.api.domain_to_DTO.GrupoModel;
import com.algaworks.algafood.api.input_model.GrupoInputModel;
import com.algaworks.algafood.api.input_model_to_domain.GrupoInputModelToDomainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algafood.domain.entitys.Grupo;
import com.algaworks.algafood.domain.services.GrupoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	@Autowired
	private GrupoModel grupoModel;
	@Autowired
	private GrupoInputModelToDomainModel inputModelToDomainModel;

	
	//--- Listar todos os grupos --->
	@GetMapping
	public List<GrupoDTO> listar(){
		return grupoModel.grupoDTOList(grupoService.getAllGroups());
	}

	//--- Buscar um grupo pelo seu id --->
	@GetMapping("/{id}")
	public GrupoDTO buscarPorId(@PathVariable Long id) {
		return grupoModel.toGrupoDTO(grupoService.getGroupById(id));
	}

	//--- Adicionar um novo grupo na base de dados --->
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoDTO addGrupo(@RequestBody @Valid  GrupoInputModel inputModel){
		Grupo grupo = inputModelToDomainModel.toDomainModel(inputModel);
		return grupoModel.toGrupoDTO(grupoService.addGroup(grupo));
	}

	//--- Atuaizar um grupo da base de dados --->
	@PutMapping("/{grupoId}")
	public GrupoDTO upDateGrupo(@PathVariable Long grupoId, @RequestBody @Valid GrupoInputModel inputModel){
		return grupoModel.toGrupoDTO(grupoService.upDate(grupoId, inputModel));
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delGrupo(@PathVariable Long grupoId){
		grupoService.deleteGrupById(grupoId);
	}

}
