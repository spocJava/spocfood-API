package com.algaworks.algafood.domain.repositorys;

import com.algaworks.algafood.domain.entitys.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

}
