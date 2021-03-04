package com.algaworks.algafood.domain.entitys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
