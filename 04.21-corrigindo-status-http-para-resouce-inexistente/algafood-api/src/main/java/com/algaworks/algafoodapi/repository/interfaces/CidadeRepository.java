package com.algaworks.algafoodapi.repository.interfaces;

import com.algaworks.algafoodapi.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    Cidade save(Cidade cidade);
    Cidade findById(Long id);
    void remove(Cidade cidade);
    List<Cidade> findAll();
}
