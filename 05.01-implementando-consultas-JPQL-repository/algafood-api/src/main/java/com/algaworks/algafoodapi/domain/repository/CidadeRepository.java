package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    Cidade save(Cidade cidade);
    Cidade findById(Long id);
    void remove(Long id);
    List<Cidade> findAll();
}
