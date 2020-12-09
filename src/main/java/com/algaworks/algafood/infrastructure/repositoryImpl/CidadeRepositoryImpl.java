package com.algaworks.algafood.infrastructure.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.models.Cidade;
import com.algaworks.algafood.domain.repositorys.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository{
	
    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }
    
    @Override
    public Cidade buscar(Long id) {
        Cidade cidade = manager.find(Cidade.class, id);
        
        if(cidade == null) {
        	throw new EmptyResultDataAccessException(1);
        }
        return cidade;
    }
    
    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }
    
    @Transactional
    @Override
    public void remover(Long id) {
        Cidade cidade = buscar(id);
        
        if(cidade == null){
        	throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);
    }
}

