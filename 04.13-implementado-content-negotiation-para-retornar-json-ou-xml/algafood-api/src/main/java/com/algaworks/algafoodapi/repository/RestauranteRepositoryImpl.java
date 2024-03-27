package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.model.Restaurante;
import com.algaworks.algafoodapi.repository.interfaces.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public void remove(Restaurante restaurante) {
        manager.remove(findById(restaurante.getId()));
    }

    @Override
    public List<Restaurante> findAll() {
        return manager.createQuery("from Restaurante", Restaurante.class)
                .getResultList();
    }
}
