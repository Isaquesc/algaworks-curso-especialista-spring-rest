package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.interfaces.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    private CozinhaRepository repository;

    @Autowired
    public CadastroCozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public Cozinha save(Cozinha cozinha) {
        return repository.save(cozinha);
    }
}
