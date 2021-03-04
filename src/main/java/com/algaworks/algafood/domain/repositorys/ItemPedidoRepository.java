package com.algaworks.algafood.domain.repositorys;

import com.algaworks.algafood.domain.entitys.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
