package com.algaworks.algafood.domain.services;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.api.input_model.ProdutoInputModel;
import com.algaworks.algafood.api.input_model_to_domain.ProdutoInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Restaurante;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.ProdutoEmUsoException;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.ProdutoNaoEncontradaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.entitys.Produto;
import com.algaworks.algafood.domain.exeptions.entity_in_used_exception.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.entity_not_found_exception.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.repositorys.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ProdutoInputModelToDomainModel produtoToDomainModel;
	
	//---- Lista todos os produtos da base de dados ---->
	public List<Produto> getAllProducts(Restaurante restaurante){
		return produtoRepository.findByRestaurante(restaurante);
	}
	
	//---- Busca um Produto pelo seu id ---->
	public Produto getProductById(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(
				restauranteId, produtoId).orElseThrow(() -> new ProdutoNaoEncontradaException(restauranteId, produtoId));
	}
	
    //---- adiciona um Produto a base de dados ---->
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
