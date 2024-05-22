package com.algaworks.algafoodapi.domain.repository.interfaces;

import com.algaworks.algafoodapi.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    Permissao save(Permissao permissao);
    Permissao findById(Long id);
    void remove(Permissao permissao);
    List<Permissao> findAll();

}
