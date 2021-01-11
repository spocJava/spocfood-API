/**
 * Essa classe é uma implemantação custumizada, onde seus métodos serão passados
 * para o repositório base da nossa entidade {@link Restaurante}. Onde usaremos critiria API
 * para implementar pesquizas dinâmicas.
 * ------------------------------------------------vamos lá!!!!!!!!!!-------
 * @author spoc
 */
package com.algaworks.algafood.infrastructure.repositoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.entitys.Restaurante;

@Component
public class RestauranteRepositoryImpl{
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Restaurante> buscarPorTaxa(String nome, Double tx_inicial, Double tx_final){
		
		var predicates = new ArrayList<Predicate>();
		// CriteriaBuilder fornece método para criar os filtros da pesquiza.
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// CriteriaQuery fornece as cláusulas da pesquiza.
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		
		//Root represento a raiz da pesquiza que nesse caso é Restaurante.
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		if(StringUtils.hasText(nome)) {
			//--> PREDICATES => são os filtros da pesquiza, que serão usado na clausula where.
			//--------Restaurantes que contenham %-?(nome)-% em seu nomes-------------
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		
		if(tx_inicial != null) {
			//--------Restaurantes que tenha texaFrete maior ou iqual a (tx_inicial)---------
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), tx_inicial));
		}
		
		if(tx_final != null) {
			//--------Restaurantes que tenha texaFrete menor ou iqual a (tx_final)---------
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), tx_final));
		}
		
		// Cláusula where do CriteriaQuery = recebe uma array de predicates(filtros) da pesquiza.
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query =  entityManager.createQuery(criteria);
		return query.getResultList();
	}
}
