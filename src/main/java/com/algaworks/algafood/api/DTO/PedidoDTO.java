package com.algaworks.algafood.api.DTO;

import com.algaworks.algafood.domain.entitys.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    private String codigo;
    private OffsetDateTime dataCriacao;
    private StatusPedido status;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private UsuarioDTO cliente;
    private RestauranteResumoDTO restaurante;
    private EnderecoDTO enderecoEntrega;
    private FormaPagamentoDTO formaPagamento;
    private List<ItemPedidoDTO> items;

}
