package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.repository.interfaces.CozinhaRepository;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void remove(Long id) {
        Cozinha cozinha = findById(id);

        if(cozinha == null)
            throw new EmptyResultDataAccessException(1);
        
        manager.remove(cozinha);
    }

    @Override
    public List<Cozinha> findAll() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }
}
