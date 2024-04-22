package com.algaworks.algafoodapi.domain.repository.interfaces;

import com.algaworks.algafoodapi.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    Cozinha save(Cozinha cozinha);
    Cozinha findById(Long id);
    void remove(Long id);
    List<Cozinha> findAll();
}
