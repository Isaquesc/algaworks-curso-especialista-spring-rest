package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.repository.interfaces.FormaPagamentoRepository;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FormaPagamento save(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    public FormaPagamento findById(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public void remove(FormaPagamento formaPagamento) {
        manager.remove(findById(formaPagamento.getId()));
    }

    @Override
    public List<FormaPagamento> findAll() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class)
                .getResultList();
    }
}
