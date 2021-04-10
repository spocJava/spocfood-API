package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.PedidoDTO;
import com.algaworks.algafood.api.DTO.PedidoResumoDTO;
import com.algaworks.algafood.api.DTO.domain_to_DTO.PedidoModel;
import com.algaworks.algafood.api.DTO.domain_to_DTO.PedidoResumoModel;
import com.algaworks.algafood.api.input_model.PedidoInputModel;
import com.algaworks.algafood.api.input_model.input_model_to_domain.PedidoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Pedido;
import com.algaworks.algafood.domain.entitys.Usuario;
import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.repositorys.PedidoRepository;
import com.algaworks.algafood.domain.services.PedidoService;
import com.algaworks.algafood.infrastructure.specs.PedidoSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoModel pedidoModel;
    private final PedidoRepository pedidoRepository;
    private final PedidoResumoModel pedidoResumoModel;
    private final PedidoInputModelToDomainModel pedidoInputModelToDomainModel;

    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(PedidoFilter filter, @PageableDefault(size = 10) Pageable pageable){
        Page<Pedido> pedidosPaginados = pedidoRepository.findAll(new PedidoSpecs().pedidoFilter(filter), pageable);
        List<PedidoResumoDTO> pedidoResumoDTOS = pedidoResumoModel.toListPedidoDTO(pedidosPaginados.getContent());
        Page<PedidoResumoDTO> pedidosPage = new PageImpl<>(pedidoResumoDTOS, pageable, pedidosPaginados.getTotalElements());
        return pedidosPage;
    }

    @GetMapping("/{codigo}")
    public PedidoDTO buscarPedido(@PathVariable String codigo){
        return pedidoModel.toPedidoDTO(pedidoService.getPedido(codigo));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO addPedido(@RequestBody @Valid PedidoInputModel pedidoInputModel){
        try{
            Pedido pedido = pedidoInputModelToDomainModel.toDomainPedido(pedidoInputModel);
            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            return pedidoModel.toPedidoDTO(pedidoService.emitirPedido(pedido));
        }catch(EntidadeNaoEncontradaExecption ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
