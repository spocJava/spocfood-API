package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.entitys.dto.VendaDiaria;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;

import java.util.List;

public interface VendasDiariasQueryService {

    List<VendaDiaria> getVendasDiariasbyRestaurant(VendaDiariaFilter filter);

}
