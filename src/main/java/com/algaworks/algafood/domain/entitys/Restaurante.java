package com.algaworks.algafood.domain.entitys;

import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@Id
	@EqualsAndHashCode.Include // somente o id será usado para criar o equals e o hashcode.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gera o id de forma automatica
	private Long id;
	
	//@NotNull // O campo não pode ser nulo
	//@NotEmpty // O campo não pode ser nullo e nen vázio
	@NotBlank
    @Column(nullable = false)
	private String nome;
	
	@PositiveOrZero
	@Column(name="taxa_frete")
	private Double taxaFrete;
	
	@JsonIgnore
	@CreationTimestamp // Cria uma data quando é criado um novo recurso.
	@Column(columnDefinition = "datetime") 
	private LocalDate dataCriacao;
	
	@JsonIgnore
	@UpdateTimestamp  // Cria uma data sempre que o recurso for atualizado.
	@Column(columnDefinition = "datetime")   
	private LocalDate dataAtualizacao;
	
	@JsonIgnore
	@Embedded         // Diz que é uma classe incorparada, é uma parte da entidade.
	private Endereco endereco;
	
	/**
	 * Varios restaurantes tem uma cozinha. (N*1)
	 * @ManyToOne = Essa anotação tem por propósito mapear o relacionamento 
	 * entre duas tabelas (restaurante/cozinha), traduzindo para o modelo Orientado a Objetos.
	 * @JoinColumn = renomeia a coluna e pode setar o notnull com o parametro nullable=false
	 */
	//@JsonIgnore
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull
	@ManyToOne // muitos pra um
	@JoinColumn(name = "cozinha_id")
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
    @ManyToMany	// muitos pra muitos 
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	/**
	 * --> Um restaurante tem vários produtos (@OneToMany).
	 * - Restaurante tem uma relação bi-direcional com Produto = <muitos produtos tem um restaurante>
	 * -----------------relação----------------------------------<um restaurante tem nuitos produtos>
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
}
