package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import com.algaworks.algafood.domain.models.Estado;

public interface EstadoRepository {
	
    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long id);
}
