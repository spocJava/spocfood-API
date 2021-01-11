package com.algaworks.algafood.domain.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.entitys.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	public List<Restaurante> findRestauranteByNomeContaining(String nome);
	public List<Restaurante> buscarPorTaxa(String nome, Double tx_inicail, Double tx_final);
}
