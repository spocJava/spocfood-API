package com.algaworks.algafood.infrastructure.specs;

import com.algaworks.algafood.domain.entitys.Pedido;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public Specification<Pedido> pedidoFilter(PedidoFilter filter){
        return (root, query, builder) ->{
            if(Pedido.class.equals(query.getResultType())){
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            var predicates = new ArrayList<Predicate>();

            if(filter.getClienteId() != null)
                predicates.add(builder.equal(root.get("cliente"), filter.getClienteId()));
            if(filter.getRestauranteId() != null)
                predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
            if(filter.getDataPedidoInicial() != null)
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataPedidoInicial()));
            if(filter.getDataPedidoFinal() != null)
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataPedidoFinal()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
