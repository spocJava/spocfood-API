package com.algaworks.algafood.domain.entitys;

import java.util.Arrays;
import java.util.List;

/***
 * @author Elizeu De Freitas Miranda (spocJava)
 */

public enum StatusPedido {

	CRIADO (),             //-- Status default
	CONFIRMADO (CRIADO),   //-- Só pode mudar para o status CONFIRMADO se o status anterior do pedido for CRIADO
	ENTREGUE (CONFIRMADO), //-- Só pode mudar para o status ENTREGUE se o status anterior do pedido for CONFIRMADO
	CANCELADO (CRIADO);    //-- Só pode mudar para o status CANCELADO se o status anterior do pedido for CRIADO

	private List<StatusPedido> statusPedidoAnterior;

	private StatusPedido (StatusPedido... statusAnteriores){
		this.statusPedidoAnterior = Arrays.asList(statusAnteriores);
	}

	public void setStatusPedidoAnterior(List<StatusPedido> statusPedidoAnterior) {
		this.statusPedidoAnterior = statusPedidoAnterior;
	}

	public Boolean notChangeToNextStatus(StatusPedido newStatus){ //-- Verifica se posso mudar para um novo status
		return !newStatus.statusPedidoAnterior.contains(this);
	}

	/**
	 * ======== Lógica do méthodo notMoveToNextStatus() =======
	 * - o methodo recebe o novo status em que o pedido estará, então pegamos esse novo status
	 * - e acessamos o(s) argumento(s) do seu construtor que contem ou não os status em que o pedido
	 * - percisa está para que possa comprir as regras de mudança de status se no novo status não conter
	 * - o status anterior correto que é comparado com o status atual da instância do pedido o status não é mududo
	 *     EXEMPLO:
	 *     public Boolean notMoveToNextStatus(StatusPedido.CONFIRMADO){
	 *      		return !CONFIRMADO.statusPedidosAnterior(CRIADO).contains(estânciaDePedido.CANCELADO);
	 *      }
	 * - como vc pode ver o statusPedidoAterior não contém o mesmo status da instância do pedido onde se que mudar
	 * - o status, porque segundo a regra não posso mudar de CANCELADO para CONFIRMADO.
	 * - para que a mudança de status podesse ocorrer o status da instância deveria ser CRIADO.
	*/
}

