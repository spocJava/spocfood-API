package com.algaworks.algafood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.entitys.Estado;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EstadoEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.repositorys.EstadoRepository;

@Service
public class CadastroEstadoService {

	
	 @Autowired 
	 private EstadoRepository estadoRepository;

	 //-----SERVICE_SALVAR_ESTADOS-----//
	 public Estado salvar(Estado estado) {
		 return estadoRepository.save(estado);
	 }
	 
	 //-----SERVICE_BUSCAR_ESTADOS-----//
	 public Estado buscar(Long id) {
		 return estadoRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
	 }
	 

	//-----SERVICE_REMOVER_ESTADOS-----//
	 public void remover(Long id) {
		 try {
			 estadoRepository.deleteById(id);
			 
		 }catch(EmptyResultDataAccessException e) {
			 throw new EstadoNaoEncontradoException(id);
			 
		 }catch(DataIntegrityViolationException e) {
			 throw new EstadoEmUsoException(id);
		 }
			 
	 }
}
