package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;

public interface VendasDiariasReportService {

    byte[] emitirVendasDiariasEmPDF(VendaDiariaFilter filter);

}
