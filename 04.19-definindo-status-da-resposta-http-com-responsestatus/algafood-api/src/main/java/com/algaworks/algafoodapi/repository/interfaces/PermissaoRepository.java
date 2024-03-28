package com.algaworks.algafoodapi.repository.interfaces;

import com.algaworks.algafoodapi.model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    Permissao save(Permissao permissao);
    Permissao findById(Long id);
    void remove(Permissao permissao);
    List<Permissao> findAll();

}
