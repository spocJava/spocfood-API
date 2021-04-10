package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.domain.entitys.dto.VendaDiaria;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.services.VendasDiariasQueryService;
import com.algaworks.algafood.domain.services.VendasDiariasReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/estatisticas")
public class EstatisticasControler {

    private final VendasDiariasQueryService vendasDiariasQueryService;
    private final VendasDiariasReportService vendasDiariasReportService;

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiariasJSON(VendaDiariaFilter filter){
        return vendasDiariasQueryService.getVendasDiariasbyRestaurant(filter);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPDF(VendaDiariaFilter filter){

        byte[] bytesDoArquivoPDF = vendasDiariasReportService.emitirVendasDiariasEmPDF(filter);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "Attachment, filename = vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesDoArquivoPDF);
    }
}
