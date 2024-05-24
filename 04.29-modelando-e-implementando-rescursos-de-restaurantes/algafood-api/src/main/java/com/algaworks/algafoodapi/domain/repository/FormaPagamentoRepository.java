package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    FormaPagamento save(FormaPagamento formaPagamento);
    FormaPagamento findById(Long id);
    void remove(FormaPagamento formaPagamento);
    List<FormaPagamento> findAll();

}
