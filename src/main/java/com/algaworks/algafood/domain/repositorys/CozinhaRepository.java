package com.algaworks.algafood.domain.repositorys;

import com.algaworks.algafood.domain.entitys.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	// Bucar cozinhas pela palavra chave que esteja em seu nome.
	public List<Cozinha> findByNomeContaining(String key_word);
	
	@Query("from Cozinha c join fetch c.restaurantes")
	public List<Cozinha> findAll();
}
