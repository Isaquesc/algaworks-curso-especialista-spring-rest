package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    Estado save(Estado estado);
    Estado findById(Long id);
    void remove(Long id);
    List<Estado> findAll();
}
