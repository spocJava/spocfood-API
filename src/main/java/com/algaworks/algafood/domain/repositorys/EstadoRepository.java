package com.algaworks.algafood.domain.repositorys;

import com.algaworks.algafood.domain.entitys.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	public List<Estado> findEstadoByNomeContaining(String nome);
}
