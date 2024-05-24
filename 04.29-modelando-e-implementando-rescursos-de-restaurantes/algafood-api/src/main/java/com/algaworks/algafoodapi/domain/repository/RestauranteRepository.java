package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    Restaurante save(Restaurante restaurante);
    Restaurante findById(Long id);
    void remove(Long id);
    List<Restaurante> findAll();

}
