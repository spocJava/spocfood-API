package com.algaworks.algafood.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.repositorys.CozinhaRepository;
import com.algaworks.algafood.domain.services.CadrastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadrastroCozinhaService cadastroCozinha;
	

	/**
	 * Esse Get que é mapeado nesse metodo retorna uma lista de cozinhas
	 * @return lista de cozinhas
	 */
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
	
	
	/**
	 * @GetMapping("/{id}") = Isso vai concatenar-se com nosso recurso /cozinhas
	 *            formando o caminho /cozinhas/id onde sera disponibilizado um single resource.
	 * @PathVariable = vincula ou faz um bind do "/{id}" com a variavel do metodo Long id, como as variaveis
	 *                 tem o mesmo nome não precisamos especificar (@PatfVariable("id") Long id). bastando so anotar o 
	 *                 parametro do metedo com (@PathVariable Long id).
	 * ResponseEntity<Cozinha> => representa uma resposta http customizada com o codigo correto que vai depender
	 *                            de uma determinda logica em relação ao recurso.
	 * @param id da cozinha a ser buscada na base.
	 * @return uma cozinha pelo seu id
	 */
	@GetMapping("/{id}")
	public Cozinha buscar(@PathVariable Long id) {
		return cadastroCozinha.serviceBuscar(id);
	}
	
	
	@GetMapping("/porNome")
	public List<Cozinha> bucarPorNome(String nome){
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	
	/**
	 * @PostMapping = mapeamento para o metodo POST para adicionar novos recursos.
	 * @ResponseStatus(HttpStatus.CREATED)= retorna o metodo HTTP correto para a operação.
	 * @RequestBody = indica que o paramentro do metodo que é um obj Cozinha vai receber um bind do corpo
	 *                de uma requizição do tipo post, para ser pesistida na base de dados.
	 * @param cozinha = @RequestBody
	 * @return pode ou nao retornar o recurso salvo
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.serviceSalvar(cozinha);
	}
	
	
	/**
	 * @param id e cozinha + @RequestBody
	 * @return Uma Cozinha modificada.
	 */
	@PutMapping("/{id}")
	public  Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		return cadastroCozinha.serviceAtualizar(id, cozinha);
	}
	
	
	/**
	 * @param id
	 * @return nada
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		cadastroCozinha.serviceExcluir(id);
	}
}
