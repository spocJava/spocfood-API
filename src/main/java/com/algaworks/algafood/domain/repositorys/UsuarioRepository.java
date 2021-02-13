package com.algaworks.algafood.domain.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafood.domain.entitys.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByEmail(String email);

}
