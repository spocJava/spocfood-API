package com.algaworks.algafood.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name="taxa_frete", nullable = false)
	private Double taxaFrete;
	
	/**
	 * Varios restaurantes tem uma cozinha. (N*1)
	 * @ManyToOne = Essa anotação tem por propósito mapear o relacionamento 
	 * entre duas tabelas (restaurante/cozinha), traduzindo para o modelo Orientado a Objetos.
	 * @JoinColumn = renomeia a coluna e pode setar o notnull com o parametro nullable=false
	 */
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

}
