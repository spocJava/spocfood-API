package com.algaworks.algafood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.entitys.Cidade;
import com.algaworks.algafood.domain.entitys.Estado;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.CidadeEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	 //------SERVICE_BUSCAR_CIDADES------//
	public Cidade buscar(Long id) {
			return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}

    //------SERVICE_SALVAR_CIDADES------//
	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscar(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	//------SERVICE_DELETAR_CIDADES-------//
	@Transactional
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e){
			throw new CidadeNaoEncontradaException(id);
			
		}catch(DataIntegrityViolationException e) {
			throw new CidadeEmUsoException(id);
		}
	}
}
