package com.algaworks.algafoodapi.repository.interfaces;

import com.algaworks.algafoodapi.model.Cozinha;
import com.algaworks.algafoodapi.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    Restaurante save(Restaurante restaurante);
    Restaurante findById(Long id);
    void remove(Restaurante restaurante);
    List<Restaurante> findAll();

}
