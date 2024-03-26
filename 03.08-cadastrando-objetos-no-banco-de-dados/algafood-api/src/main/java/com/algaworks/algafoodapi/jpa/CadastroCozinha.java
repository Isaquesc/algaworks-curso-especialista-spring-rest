package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> findAll() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    public Cozinha findById(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    public void remove(Cozinha cozinha) {
        manager.remove(findById(cozinha.getId()));
    }

}
