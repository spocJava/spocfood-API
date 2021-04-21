package com.algaworks.algafood.core.listener;

import com.algaworks.algafood.core.events.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.entitys.Pedido;
import com.algaworks.algafood.domain.services.EnvioEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class EnviarEmailPedidoConfirmadoListener {

    private final EnvioEmailService emailService;

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event){

        Pedido pedido = event.getPedido();

        var menssagem = EnvioEmailService.Menssagem.builder()
                .assunto(String.format("Restaurante %s - Pedido confirmado",pedido.getRestaurante().getNome()))
                .conteudo("email_confirmacao_de_pedido.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        emailService.enviarEmail(menssagem);
    }
}
