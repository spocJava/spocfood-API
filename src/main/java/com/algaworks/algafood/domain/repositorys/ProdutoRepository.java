package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	  @Query("from Produto p join fetch p.restaurante") 
	  public List<Produto> findAll();
	 
}
