package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Permissao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Permissao save(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    public Permissao findById(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public void remove(Permissao permissao) {
        manager.remove(findById(permissao.getId()));
    }

    @Override
    public List<Permissao> findAll() {
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }
}
