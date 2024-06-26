package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Estado;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void remove(Long id) {
        Estado estado = findById(id);

        if(estado == null)
            throw new EmptyResultDataAccessException(1);

        manager.remove(findById(id));
    }

    @Override
    public List<Estado> findAll() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }
}
