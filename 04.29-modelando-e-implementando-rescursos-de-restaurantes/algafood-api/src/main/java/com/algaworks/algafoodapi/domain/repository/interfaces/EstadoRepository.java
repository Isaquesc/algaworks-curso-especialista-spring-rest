package com.algaworks.algafoodapi.domain.repository.interfaces;

import com.algaworks.algafoodapi.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    Estado save(Estado estado);
    Estado findById(Long id);
    void remove(Estado estado);
    List<Estado> findAll();
}
