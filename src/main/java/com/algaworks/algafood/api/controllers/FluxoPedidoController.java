package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.domain.services.FluxoPedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigo}")
@AllArgsConstructor
public class FluxoPedidoController {

    private final FluxoPedidoService changePedido;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeToConfirmed(@PathVariable String codigo){
        changePedido.setStatusToConfirmed(codigo);
    }

    @PutMapping("/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeToDelivered(@PathVariable String codigo){
        changePedido.setStatusToDelivered(codigo);
    }

    @PutMapping("/cancelado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeToCanceled(@PathVariable String codigo){
        changePedido.setStatusToCanceled(codigo);
    }
}
