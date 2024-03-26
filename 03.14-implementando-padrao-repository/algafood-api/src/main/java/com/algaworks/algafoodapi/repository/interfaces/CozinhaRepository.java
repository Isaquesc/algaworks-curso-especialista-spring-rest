package com.algaworks.algafoodapi.repository.interfaces;

import com.algaworks.algafoodapi.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    Cozinha save(Cozinha cozinha);
    Cozinha findById(Long id);
    void remove(Cozinha cozinha);
    List<Cozinha> findAll();
}
