package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTO.CozinhaDTO;
import com.algaworks.algafood.api.domain_to_DTO.CozinhaModel;
import com.algaworks.algafood.api.input_model.CozinhaImputModel;
import com.algaworks.algafood.api.input_model_to_domain.CozinhaInputModelToDomainModel;
import com.algaworks.algafood.domain.entitys.Cozinha;
import com.algaworks.algafood.domain.repositorys.CozinhaRepository;
import com.algaworks.algafood.domain.services.CadrastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadrastroCozinhaService cadastroCozinha;

	@Autowired
	CozinhaModel cozinhaModel;

	@Autowired
	CozinhaInputModelToDomainModel cozinhaInputModelToDomainModel;
	

	/**
	 * Esse Get que é mapeado nesse metodo retorna uma lista de cozinhas
	 * @return lista de cozinhas
	 */
	@GetMapping
	public Page<CozinhaDTO> listar(Pageable pageable){
		Page<Cozinha> cozinhaPage = cozinhaRepository.findAll(pageable);
		List<CozinhaDTO> cozinhaDTO = cozinhaModel.toListCozinhaDTO(cozinhaPage.getContent());
		Page<CozinhaDTO> cozinhaModelPage = new PageImpl<>(cozinhaDTO, pageable, cozinhaPage.getTotalElements());
		return cozinhaModelPage;
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
	public CozinhaDTO buscar(@PathVariable Long id) {
		return cozinhaModel.toCozinhaDTO(cadastroCozinha.serviceBuscar(id));
	}
	
	
	@GetMapping("/porNome")
	public List<CozinhaDTO> bucarPorNome(String nome){
		return cozinhaModel.toListCozinhaDTO(cozinhaRepository.findByNomeContaining(nome));
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
	public CozinhaDTO adicionar(@RequestBody @Valid  CozinhaImputModel cozinhaInputModel) {
		Cozinha cozinha = cozinhaInputModelToDomainModel.toCozinhaDomainModel(cozinhaInputModel);
		return cozinhaModel.toCozinhaDTO(cadastroCozinha.serviceSalvar(cozinha));
	}
	
	
	/**
	 * @param id e cozinha + @RequestBody
	 * @return Uma Cozinha modificada.
	 */
	@PutMapping("/{id}")
	public  CozinhaDTO atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaImputModel cozinhaImputModel) {
		return cozinhaModel.toCozinhaDTO(cadastroCozinha.serviceAtualizar(id, cozinhaImputModel));
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
