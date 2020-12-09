package com.algaworks.algafood.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
	@EqualsAndHashCode.Include // parametro que sera usado no equal/hash
	@GeneratedValue(strategy = GenerationType.IDENTITY)// gera o ID automaticamente
	private long id;
	
	@Column(nullable = false)
	private String nome;
		
}
