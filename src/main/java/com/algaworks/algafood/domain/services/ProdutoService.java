package com.algaworks.algafood.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exeptions.EntidadeEmUsoExeption;
import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaExecption;
import com.algaworks.algafood.domain.models.Produto;
import com.algaworks.algafood.domain.repositorys.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	//---- Lista todos os produtos da base de dados ---->
	public List<Produto> getAllProducts(){
		return produtoRepository.findAll();
	}
	
	//---- Busca um Produto pelo seu id ---->
	public Optional<Produto> getProductById(Long id) {
		try {
			return produtoRepository.findById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(String.format(
					"Não existe um Produto com o ID=%d, na base de dados.", id));
		}
	}
	
    //---- adiciona um Produto a base de dados ---->
	public Produto addProduct(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	//---- Remove um Produto pelo seu id ---->
	public void deleteProductById(Long id) {
		try {
			produtoRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExecption(String.format(
					"Não existe um Produto com o ID=%d, na base de dados.", id));
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(String.format(
					"Não foi possível remover o 'Produto' de ID=%d, Pois está em uso em outra entidade", id));
		}
	}
	
	
}
