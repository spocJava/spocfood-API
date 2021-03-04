package com.algaworks.algafood.domain.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@Email
	private String email;

	@NotBlank
	private String senha;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDate dataCadrastro;

	@JsonIgnore
	@ManyToMany()
	private Set<Grupo> grupos = new HashSet<>();

	//-- Adiciona um grupo de permissão ao usuário
	public Boolean addGrupo(Grupo grupo){
		return getGrupos().add(grupo);
	}

	//-- Remove o usuário de um grupo de permissão
	public Boolean removeGrupo(Grupo grupo){
		return getGrupos().remove(grupo);
	}
}
