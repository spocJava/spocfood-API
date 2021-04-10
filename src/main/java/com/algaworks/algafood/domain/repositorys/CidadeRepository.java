package com.algaworks.algafood.domain.repositorys;

import com.algaworks.algafood.domain.entitys.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    /**
     * Query Metods => pesquiza cidades pelo nome.
     */
	public List<Cidade> findByNomeContaining(String nome);
	
	@Query("from Cidade c join fetch c.estado")
	public List<Cidade> findAll();
	
}	