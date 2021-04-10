package com.algaworks.algafood.domain.entitys;

import com.algaworks.algafood.domain.exeptions.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigo;

	private BigDecimal subTotal;

	private BigDecimal taxaFrete;

	private BigDecimal valorTotal;
	
	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@OneToMany(mappedBy = "pedido" , cascade = CascadeType.ALL)
	private List<ItemPedido> items = new ArrayList<>();

	//-- Gera o código uuid da instacia do pedido
	@PrePersist //-- antes do jpa pesistir a entidide gera o codigo uuid
	private void setUUID(){
		setCodigo(UUID.randomUUID().toString());
	}

	public void calcularValorTotal(){
		getItems().forEach(ItemPedido::calcValorTotal);//-- calcula o valor tatol de cada item de pedido
		this.subTotal = getItems().stream()
				.map(item -> item.getPrecoTotal()) //-- calcula o subtotal do pedido que é a soma de todos os items
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		this.valorTotal = this.subTotal.add(this.taxaFrete); //-- calculao valor total do pedido subtotal + taxaFrete
	}

	public void setTaxaFrete(){  //-- set a taxa frete para esse pedido de acordo com o restaurante onde foi feito
		this.setTaxaFrete((BigDecimal.valueOf(getRestaurante().getTaxaFrete())));
	}

	public void addItemsPedidos(){
		getItems().forEach(item -> item.setPedido(this)); //-- add items pedidos ao pedido
	}

	//-- Muda para o status confirmado
	public void changeToConfirmed(){
		this.setStatus(StatusPedido.CONFIRMADO);
		this.setDataConfirmacao(OffsetDateTime.now());
	}

	//-- Muda para o status entregue
	public void changeToDelivered(){
		this.setStatus(StatusPedido.ENTREGUE);
		this.setDataEntrega(OffsetDateTime.now());
	}

	//-- Muda para o status cancelado
	public void changeToCanceled(){
		this.setStatus(StatusPedido.CANCELADO);
		this.setDataCancelamento(OffsetDateTime.now());
	}

	//-- Verifica se pode mudar ou não de status
	public void setStatus(StatusPedido newStatus){
		if (getStatus().notChangeToNextStatus(newStatus)){
			throw new NegocioException(String.format(
					"O pedido de id %s não pode ser alterado de %s para %s", getId(), getStatus(), newStatus));
		}
		this.status = newStatus;
	}
}
