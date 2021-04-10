package com.algaworks.algafood.domain.repositorys;

import com.algaworks.algafood.domain.entitys.Permicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermicaoRepository extends JpaRepository<Permicao, Long>{

}
