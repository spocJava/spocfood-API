package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.input_model.ProdutoInputModel;
import com.algaworks.algafood.api.input_model.input_model_to_domain.ProdutoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Produto;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.ProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.repositorys.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ProdutoInputModelToDomainModel produtoToDomainModel;
	

	public List<Produto> getAllProducts(Restaurante restaurante, boolean incluirInativos){

		List<Produto> produtos;

		if(incluirInativos == false){
			produtos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		else {
			produtos = produtoRepository.findByRestaurante(restaurante);
		}

		return produtos;
	}
	

	public Produto getProductById(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(
				restauranteId, produtoId).orElseThrow(() -> new ProdutoNaoEncontradaException(restauranteId, produtoId));
	}


	@Transactional
	public Produto addProduct(Produto produto) {
		return produtoRepository.save(produto);
	}


	@Transactional
	public Produto upDateProduct(Long restId ,Long productId, ProdutoInputModel product){
		Produto produtoAtual = getProductById(restId, productId);
		produtoToDomainModel.inputToDomail(product);
		produtoToDomainModel.copyProperties(product, produtoAtual);
		return addProduct(produtoAtual);
	}
}
