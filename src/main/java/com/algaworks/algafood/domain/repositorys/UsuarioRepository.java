package com.algaworks.algafood.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafood.domain.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
