/**
 * Essa classe é uma implemantação custumizada, onde seus métodos serão passados
 * para o repositório base da nossa entidade {@link Restaurante}. Onde usaremos critiria API
 * para implementar pesquizas dinâmicas.
 * ------------------------------------------------vamos lá!!!!!!!!!!-------
 * @author spoc
 */
package com.algaworks.algafood.infrastructure.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.models.Restaurante;

@Component
public class RestauranteRepositoryImpl{
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Restaurante> buscarPorTaxa(String nome, Double tx_inicial, Double tx_final){
		
		// CriteriaBuilder fornece método para criar os filtros da pesquiza.
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// CriteriaQuery fornece as cláusulas da pesquiza.
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		
		//Root represento a raiz da pesquiza que nesse caso é Restaurante.
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		//--> PREDICATES => são os filtros da pesquiza, que serão usado na clausula where.
		//--------Restaurantes que contenham %-?(nome)-% em seu nomes-------------
		Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
		//--------Restaurantes que tenha texaFrete maior ou iqual a (tx_inicial)---------
		Predicate tx_InicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), tx_inicial);
		//--------Restaurantes que tenha texaFrete menor ou iqual a (tx_final)---------
		Predicate tx_finalPredicate = builder.lessThanOrEqualTo(root.get("taxaFrete"), tx_final);
		
		// Cláusula where do CriteriaQuery = recebe uma array de predicates(filtros) da pesquiza.
		criteria.where(nomePredicate, tx_InicialPredicate, tx_finalPredicate);
		
		TypedQuery<Restaurante> query =  entityManager.createQuery(criteria);
		return query.getResultList();
	}
}
