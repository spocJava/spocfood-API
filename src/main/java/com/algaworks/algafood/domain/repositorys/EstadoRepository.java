package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.models.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	public List<Estado> findEstadoByNomeContaining(String nome);
}
