package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.ProdutoDTO;
import com.algaworks.algafood.api.domain_to_DTO.ProdutoModel;
import com.algaworks.algafood.api.input_model.ProdutoInputModel;
import com.algaworks.algafood.api.input_model_to_domain.ProdutoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Produto;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.services.CadastroRestauranteService;
import com.algaworks.algafood.domain.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	@Autowired
	private ProdutoModel produtoModel;
	@Autowired
	private ProdutoInputModelToDomainModel produtoToDomainModel;

	
	//---- Lista todos os produtos da base de dados --->
	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = cadastroRestauranteService.serviceBuscar(restauranteId);
		return produtoModel.toListProdutoDTO(produtoService.getAllProducts(restaurante));
	}
	
	//---- Busca Produtos pelo seu id ---->
	@GetMapping("/{productId}")
	public ProdutoDTO buscarPorId(@PathVariable Long restauranteId  ,@PathVariable Long productId) {
		return produtoModel.toProdutoDTO(produtoService.getProductById(restauranteId, productId));
	}

	//---- Adicina um novo produto  ---->
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO createProduct(@PathVariable Long restauranteId,
			@RequestBody @Valid  ProdutoInputModel productInput){
		Restaurante restaurante = cadastroRestauranteService.serviceBuscar(restauranteId);
		Produto product = produtoToDomainModel.inputToDomail(productInput);
		product.setRestaurante(restaurante);

		return produtoModel.toProdutoDTO(produtoService.addProduct(product)) ;
	}

	//---- Atualiza um produto  ---->
	@PutMapping("/{productId}")
	public ProdutoDTO upDateProduct(
			@PathVariable Long restauranteId, @PathVariable Long productId,
			@RequestBody @Valid  ProdutoInputModel productInput){
		return produtoModel.toProdutoDTO(produtoService.upDateProduct(restauranteId,productId, productInput));
	}
}
