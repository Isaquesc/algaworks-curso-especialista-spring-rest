package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.model.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> findAll() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

}
