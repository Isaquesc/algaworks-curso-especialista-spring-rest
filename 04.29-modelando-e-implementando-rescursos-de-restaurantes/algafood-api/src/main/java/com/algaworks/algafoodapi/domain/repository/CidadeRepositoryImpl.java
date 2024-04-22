package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.interfaces.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Cidade save(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    public Cidade findById(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public void remove(Cidade cidade) {
        manager.remove(findById(cidade.getId()));
    }

    @Override
    public List<Cidade> findAll() {
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }
}
