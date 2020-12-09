package com.algaworks.algafood.infrastructure.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.models.Cozinha;
import com.algaworks.algafood.domain.repositorys.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{
	
	@Autowired
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	/**
	 * Metodo para adicionar cozinhas em nossa base de dados.
	 * @Transactional usado para criar uma transação com banco de dados -> ou seja 
	 *                esse metodo vai ser executado dentro dessa transação.
	 * @param cozinha
	 * @return retorna uma instancia de Cozinha pesistida no banco de dados.
	 */
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	/**
	 * Metodo para fazer consulta no nosso banco pelo ID, vai retorna somente um item
	 * @param id do item a ser buscado
	 * @return item da base de dados com esse ID.
	 */
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	/**
	 * Metodo para deletarmos um item da base de dados
	 * @implNote para que esse metodo funcione o objeto passado como parametro tem que 
	 *           esta gerenciado pelo contesto de pesistencia do JPA. se estiver no ciclo
	 *           como transiente não sera possivel remover o item.
	 * @param cozinha
	 * @return null
	 */
	@Transactional
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cozinha);
	}

}
