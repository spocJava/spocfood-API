package com.algaworks.algafood.api.DTO;

import com.algaworks.algafood.domain.entitys.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoResumoDTO {

    private String codigo;
    private OffsetDateTime dataCriacao;
    private StatusPedido status;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private UsuarioDTO cliente;
    private RestauranteResumoDTO restaurante;

}
