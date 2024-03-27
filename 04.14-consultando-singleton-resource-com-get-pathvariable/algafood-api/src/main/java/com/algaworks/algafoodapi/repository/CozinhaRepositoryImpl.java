package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.model.Cozinha;
import com.algaworks.algafoodapi.repository.interfaces.CozinhaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Cozinha save(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Override
    public Cozinha findById(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public void remove(Cozinha cozinha) {
        manager.remove(findById(cozinha.getId()));
    }

    @Override
    public List<Cozinha> findAll() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }
}
