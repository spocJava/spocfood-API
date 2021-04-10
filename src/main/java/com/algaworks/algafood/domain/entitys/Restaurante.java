package com.algaworks.algafood.domain.entitys;

import com.algaworks.algafood.domain.exeptions.NegocioException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@Id
	@EqualsAndHashCode.Include // somente o id será usado para criar o equals e o hashcode.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gera o id de forma automatica
	private Long id;

	private String nome;
	
	private Double taxaFrete;
	
	private Boolean ativo = Boolean.TRUE;

	private Boolean aberto = Boolean.FALSE;
	
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
	//@Valid
	//@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	//@NotNull
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
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	/**
	 * --> Um restaurante tem vários produtos (@OneToMany).
	 * - Restaurante tem uma relação bi-direcional com Produto = <muitos produtos tem um restaurante>
	 * -----------------relação----------------------------------<um restaurante tem nuitos produtos>
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private Set<Produto> produtos = new HashSet<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
	joinColumns = @JoinColumn(name = "restaurante_id"),
	inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();

	//-- chega se a forma de pagamento é aceita pelo restaurante
	public void formaDePagamentoNaoAceita(FormaPagamento formaPagamento){
		if(!this.getFormasPagamento().contains(formaPagamento)){
			throw new NegocioException(String.format("A forma de pagamento <%s> não é aceita nesse restaurante, " +
					formaPagamento.getDescricao()));
		}
	}

	//-- Ativa o restaurante
	public void ativar() {
		setAtivo(true);
	}

	//-- Desativa o restaurante
	public void inativar() {
		setAtivo(false);
	}

	//-- Associa uma nova forma de pagamento ao restaurante
	public boolean associarFormaPagamento(FormaPagamento formaPagamento){
		return getFormasPagamento().add(formaPagamento);
	}

	//-- Desassocia uma forma de pagamento do restaurante
	public boolean desassociarFormaPagamento(FormaPagamento formaPagamento){
		return getFormasPagamento().remove(formaPagamento);
	}

	//-- Fecha o restaurante
	public void fecharRestaurante(){
		setAberto(false);
	}

	//-- Abre o restaurante
	public void abrirRestaurante(){
		setAberto(true);
	}

	//-- Addiciona o responsável pelo restaurante
	public Boolean addResponsavel(Usuario usuario){
		return getResponsaveis().add(usuario);
	}

	//-- remove o responsável pelo restaurante
	public Boolean removerResponsavel(Usuario usuario){
		return getResponsaveis().remove(usuario);
	}
}
