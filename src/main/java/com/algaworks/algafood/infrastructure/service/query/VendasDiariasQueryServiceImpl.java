package com.algaworks.algafood.infrastructure.service.query;

import com.algaworks.algafood.domain.entitys.Pedido;
import com.algaworks.algafood.domain.entitys.StatusPedido;
import com.algaworks.algafood.domain.entitys.dto.VendaDiaria;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.services.VendasDiariasQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendasDiariasQueryServiceImpl implements VendasDiariasQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> getVendasDiariasbyRestaurant(VendaDiariaFilter filter) {

        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var predicates = new ArrayList<Predicate>();

        var functionDateOfTheDataBase = builder.function(
                "date", Date.class, root.get("dataCriacao"));

        var selection = builder.construct(
                VendaDiaria.class,
                functionDateOfTheDataBase,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        if(filter.getRestauranteId() != null){
           predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
        }
        if(filter.getDataPedidoInicial() != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataPedidoInicial()));
        }
        if(filter.getDataPedidoFinal() != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataPedidoInicial()));
        }
        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateOfTheDataBase);

        return manager.createQuery(query).getResultList();
    }
}
