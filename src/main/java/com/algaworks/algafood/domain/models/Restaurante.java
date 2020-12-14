package com.algaworks.algafood.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	// Diz que é uma classe incorparada, é uma parte da entidade.
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	/**
	 * Varios restaurantes tem uma cozinha. (N*1)
	 * @ManyToOne = Essa anotação tem por propósito mapear o relacionamento 
	 * entre duas tabelas (restaurante/cozinha), traduzindo para o modelo Orientado a Objetos.
	 * @JoinColumn = renomeia a coluna e pode setar o notnull com o parametro nullable=false
	 */
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	/**
	 * --> Vários restaurantes aceitam vários formas de pagamento (@ManyToMany).
	 * @ManyToMany ==> nessa relação uma tabela é criada com as foren key das tabelas relacionadas.
	 * ___________Caso queira modificar os nomes use o código abaixo______________ 
	 * @JoinTable(name = "Nome_da_tabela", 
	 * 	joinColumns = 
	 * 		@JoinColumn(name = "Nome_da_coluna", referencedColumnName = "ID da tabela que dona da relação"), 
	 *  inverseJoinColumns = 
	 *  	@JoinColumn(name ="Nome_da_coluna", referencedColumnName = "ID da outra tabela que faz parte da relação"))
	 */
	@JsonIgnore
    @ManyToMany	
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

}
