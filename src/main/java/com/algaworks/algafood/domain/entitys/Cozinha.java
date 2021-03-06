package com.algaworks.algafood.domain.entitys;

import com.algaworks.algafood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Usando o lombok é muito bom pois nos ajuda com codigos bodyplates como setter getter
 * tostring equal e hashcode e construtores.
 * @author spoc
 */

@Data // gera gettes/setter, equals/hashcode, toString e etc automaticamente
@EqualsAndHashCode(onlyExplicitlyIncluded = true)// especificando qual paramentro usar no equals/hash
@Entity 
public class Cozinha {
	
	@Id
	@NotNull(groups = Groups.CozinhaId.class)
	@EqualsAndHashCode.Include // parametro que sera usado no equal/hash
	@GeneratedValue(strategy = GenerationType.IDENTITY)// gera o ID automaticamente
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	/**
	 * --> Uma cozinha tem zero ou varios restaurantes (@OneToMany).
	 * [- Aqui temos um relacionamento bi-direcional entre as tabelas Restaurante e cozinha
	 *    pois Restaurante tem um relacionamento oposto com a cozinha 
	 *    --> Varios restaurantes tem uma cozinha (@ManyToOne). -]
	 * @JsonIgnore ==> Não serializa o objeto cozinha com o relacionamento na representação.
	 * OBS --> As vezes vc faz um relacionamento bi-direcional, não para mostrar na representação do objeto.
	 *         Mas sim para usar em uma regra de negócio do seu sistema, porisso o @JsonIgnore.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();
		
}
