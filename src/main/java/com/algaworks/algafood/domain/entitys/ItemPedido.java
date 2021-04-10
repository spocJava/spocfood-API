package com.algaworks.algafood.domain.entitys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	@Column(nullable = false)
	private BigDecimal precoTotal;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

	public void calcValorTotal(){
		if(this.getQuantidade() == null)
			this.setQuantidade(0);
		else if(this.getPrecoUnitario() == null)
			this.setPrecoUnitario(BigDecimal.ZERO);

		this.setPrecoTotal(BigDecimal.valueOf(this.getQuantidade()).multiply(this.getPrecoUnitario()));
	}
}
