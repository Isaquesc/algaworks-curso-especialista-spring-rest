package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.interfaces.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    private CozinhaRepository repository;

    @Autowired
    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public Cozinha save(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void remove(Long cozinhaId) {
        try {
            repository.remove(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEnconstradaException(
                    String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }
}
