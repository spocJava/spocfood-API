package com.algaworks.algafood.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.entitys.Produto;
import com.algaworks.algafood.domain.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	//---- Lista todos os produtos da base de dados --->
	@GetMapping
	public List<Produto> listar(){
		return produtoService.getAllProducts();
	}
	
	//---- Busca Produtos pelo seu id ---->
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		Optional<Produto> produtoOptional = produtoService.getProductById(id);
		if(produtoOptional.isPresent()) {
			return ResponseEntity.ok(produtoOptional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
