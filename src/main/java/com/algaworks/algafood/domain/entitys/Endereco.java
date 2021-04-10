package com.algaworks.algafood.domain.entitys;

import lombok.Data;

import javax.persistence.*;

/**
 * Essa classe funciona como um componente que compõe uma entidade que a incorpore.
 * ou seja a entidade que quizer ter endereço em sua tabela é só embutir  Endereço em si, pois ela e incor-
 * -porável.
 * @author spoc
 *
 */
@Data
@Embeddable
public class Endereco {

	@Column(name = "end_cep")
	private String cep;
	
	@Column(name = "end_logradouro")
	private String logradouro;
	
	@Column(name = "end_numero")
	private String numero;
	
	@Column(name = "end_complemento")
	private String complemento;
	
	@Column(name = "end_bairro")
	private String bairro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
}
