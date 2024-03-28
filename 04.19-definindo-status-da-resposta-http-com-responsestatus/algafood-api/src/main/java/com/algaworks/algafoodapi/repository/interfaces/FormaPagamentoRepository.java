package com.algaworks.algafoodapi.repository.interfaces;

import com.algaworks.algafoodapi.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    FormaPagamento save(FormaPagamento formaPagamento);
    FormaPagamento findById(Long id);
    void remove(FormaPagamento formaPagamento);
    List<FormaPagamento> findAll();

}
