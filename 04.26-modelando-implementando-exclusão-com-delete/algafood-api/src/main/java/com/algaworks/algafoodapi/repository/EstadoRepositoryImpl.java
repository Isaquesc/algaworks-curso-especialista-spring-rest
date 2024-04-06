package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.model.Estado;
import com.algaworks.algafoodapi.repository.interfaces.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Estado save(Estado estado) {
        return manager.merge(estado);
    }

    @Override
    public Estado findById(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public void remove(Estado estado) {
        manager.remove(findById(estado.getId()));
    }

    @Override
    public List<Estado> findAll() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }
}
