package com.algaworks.algafood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exeptions.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.Estado;
import com.algaworks.algafood.domain.repositorys.EstadoRepository;

@Service
public class CadastroEstadoService {

	
	 @Autowired 
	 private EstadoRepository estadoRepository;

	 //-----SERVICE_SALVAR_ESTADOS-----//
	 public Estado salvar(Estado estado) {
		 return estadoRepository.salvar(estado);
	 }
	 
	 //-----SERVICE_BUSCAR_ESTADOS-----//
	 public Estado bucar(Long id) {
		 return estadoRepository.buscar(id);
	 }
	 

	//-----SERVICE_REMOVER_ESTADOS-----//
	 public void remover(Long id) {
		 try {
			 estadoRepository.remover(id);
			 
		 }catch(EmptyResultDataAccessException e) {
			 throw new EntidadeNaoEncontradaExecption(
					 String.format("Não existe um estado com o código %d na base de dados.", id));
			 
		 }catch(DataIntegrityViolationException e) {
			 throw new EntidadeEmUsoExeption(
					 String.format("O estado de código %d não pode ser excluido, pois está em uso.", id));
		 }
	 }
}
