package com.algaworks.algafood.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.models.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    // Spring Data JPA -> usando os metodos da interface JpaRepository.
}
