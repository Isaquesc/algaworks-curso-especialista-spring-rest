package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.model.FormaPagamento;
import com.algaworks.algafoodapi.repository.interfaces.FormaPagamentoRepository;
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
