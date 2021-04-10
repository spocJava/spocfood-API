package com.algaworks.algafood.infrastructure.service.report;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.services.VendasDiariasReportService;
import org.springframework.stereotype.Service;

@Service
public class PDFVendasDiariasReportServiceImpl implements VendasDiariasReportService {

    @Override
    public byte[] emitirVendasDiariasEmPDF(VendaDiariaFilter filter) {

        return new byte[0];
    }
}
