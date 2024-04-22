package com.algaworks.algafoodapi.domain.repository.interfaces;

import com.algaworks.algafoodapi.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    Cidade save(Cidade cidade);
    Cidade findById(Long id);
    void remove(Cidade cidade);
    List<Cidade> findAll();
}
