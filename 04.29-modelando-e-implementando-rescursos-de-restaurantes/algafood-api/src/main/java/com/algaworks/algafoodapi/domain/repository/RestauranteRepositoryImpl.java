package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Restaurante save(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Override
    public Restaurante findById(Long id) {
        return manager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        manager.remove(findById(id));
    }

    @Override
    public List<Restaurante> findAll() {
        return manager.createQuery("from Restaurante", Restaurante.class)
                .getResultList();
    }
}
