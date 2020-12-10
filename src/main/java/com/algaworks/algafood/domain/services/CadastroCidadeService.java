package com.algaworks.algafood.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exeptions.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.Cidade;
import com.algaworks.algafood.domain.models.Estado;
import com.algaworks.algafood.domain.repositorys.CidadeRepository;
import com.algaworks.algafood.domain.repositorys.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	 //------SERVICE_BUSCAR_CIDADES------//
	public Optional<Cidade> buscar(Long id) {
		try {
			return cidadeRepository.findById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(
					String.format("Não existe uma cidade com o código %d.", id));
		}
	}

    //------SERVICE_SALVAR_CIDADES------//
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaExecption(
					String.format("Não existe um estado de código %d.", estadoId));
		}
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	//------SERVICE_DELETAR_CIDADES-------//
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaExecption(
					String.format("Não existe uma cidade com o código %d na base de dados.", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					String.format("Não é possivel remover a cidade de código %d, pois está em uso.", id));
		}
	}
}
