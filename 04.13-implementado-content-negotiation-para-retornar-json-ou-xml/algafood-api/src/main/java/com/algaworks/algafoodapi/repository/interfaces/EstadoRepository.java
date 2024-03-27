package com.algaworks.algafoodapi.repository.interfaces;

import com.algaworks.algafoodapi.model.Estado;

import java.util.List;

public interface EstadoRepository {
    Estado save(Estado estado);
    Estado findById(Long id);
    void remove(Estado estado);
    List<Estado> findAll();
}
