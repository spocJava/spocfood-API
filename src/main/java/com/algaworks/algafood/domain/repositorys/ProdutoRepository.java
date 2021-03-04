package com.algaworks.algafood.domain.repositorys;

import com.algaworks.algafood.domain.entitys.Produto;
import com.algaworks.algafood.domain.entitys.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	//-- buscar um produto de determindo restaurante
	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);

	//-- lista todos os produtos de um restaurante
	List<Produto> findByRestaurante(Restaurante restaurante);
}
