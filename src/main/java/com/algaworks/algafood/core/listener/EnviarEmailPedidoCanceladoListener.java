package com.algaworks.algafood.core.listener;

import com.algaworks.algafood.core.events.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.entitys.Pedido;
import com.algaworks.algafood.domain.services.EnvioEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class EnviarEmailPedidoCanceladoListener {

    private final EnvioEmailService emailService;

    @TransactionalEventListener
    public void aoCancelarPedido(PedidoCanceladoEvent event){

        Pedido pedido = event.getPedido();

        var menssage = EnvioEmailService.Menssagem.builder()
                .assunto(String.format("Restaurante %s - Pedido Cancelado", pedido.getRestaurante().getNome()))
                .conteudo("email_cancelamento_de_pedido.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();
        emailService.enviarEmail(menssage);
    }
}
