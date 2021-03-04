package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.input_model_to_domain.PedidoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.*;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.PedidoEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.PedidoNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.PedidoRepository;
import com.algaworks.algafood.domain.repositorys.filter.PedidoFilter;
import com.algaworks.algafood.infrastructure.specs.PedidoSpecs;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CadastroRestauranteService cadastroRestauranteService;
    private final CadastroCidadeService cadastroCidadeService;
    private final FormaPagamentoService formaPagamentoService;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;


    public List<Pedido> listar(PedidoFilter filter){
        return pedidoRepository.findAll(new PedidoSpecs().pedidoFilter(filter));
    }

    public Pedido getPedido(String codigo){
        return pedidoRepository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradaException(codigo));
    }

    @Transactional
    public Pedido emitirPedido(Pedido pedido){
        validarPedido(pedido);
        validarItemsPedido(pedido);

        pedido.setTaxaFrete(BigDecimal.valueOf(pedido.getRestaurante().getTaxaFrete()));
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido upDatePedido(String codigo, Pedido pedido){
        Pedido pedidoAtual = getPedido(codigo);
        BeanUtils.copyProperties(pedido, pedidoAtual, "id");
        return emitirPedido(pedidoAtual);
    }


    //-- validar entidades relacionadas ao pedido
    public void validarPedido(Pedido pedido){
        Restaurante restaurante = cadastroRestauranteService.serviceBuscar(pedido.getRestaurante().getId());
        Cidade cidade = cadastroCidadeService.buscar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = usuarioService.getUserById(pedido.getCliente().getId());
        FormaPagamento formaPagamento = formaPagamentoService.getFormaPagamento(pedido.getFormaPagamento().getId());

        pedido.setRestaurante(restaurante);
        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);

        restaurante.formaDePagamentoNaoAceita(formaPagamento);
        pedido.setFormaPagamento(formaPagamento);

    }

    //-- Verifica se o produto existe em um determinda restaurante
    public void validarItemsPedido(Pedido pedido){
        pedido.getItems().forEach(itemPedido -> {
            Produto produto = produtoService.getProductById(pedido.getRestaurante().getId(),
                    itemPedido.getProduto().getId());

            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setPrecoUnitario(produto.getPreco());
        });
    }
}
