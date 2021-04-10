package com.algaworks.algafood.domain.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;

	@JsonIgnore
	@ManyToMany
	private Set<Permicao> permicoes = new HashSet<>();

	//-- Adiciona permição --//
	public Boolean addPermicao(Permicao permicao){
		return getPermicoes().add(permicao);
	}

	//-- Remove permição --//
	public Boolean removePermicao(Permicao permicao){
		return getPermicoes().remove(permicao);
	}
}
