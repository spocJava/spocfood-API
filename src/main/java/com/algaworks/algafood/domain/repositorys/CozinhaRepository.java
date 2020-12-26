package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.models.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	// Bucar cozinhas pela palavra chave que esteja em seu nome.
	public List<Cozinha> findByNomeContaining(String key_word);
	
	@Query("from Cozinha c join fetch c.restaurantes")
	public List<Cozinha> findAll();
}
