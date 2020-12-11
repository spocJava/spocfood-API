package com.algaworks.algafood.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.models.Permicao;

@Repository
public interface PermicaoRepository extends JpaRepository<Permicao, Long>{

}
