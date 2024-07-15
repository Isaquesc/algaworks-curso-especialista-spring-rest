package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    private CidadeRepository repositoryCidade;

    private EstadoService estadoService;

    @Autowired
    public CidadeService(CidadeRepository repositoryCidade, EstadoService estadoService) {
        this.repositoryCidade = repositoryCidade;
        this.estadoService = estadoService;
    }

    public Cidade save(Cidade cidade) {
        Estado estado = estadoService.buscarOuFalhar(cidade.getEstado().getId());
        cidade.setEstado(estado);

        return repositoryCidade.save(cidade);
    }

    public void remove(Long cidadeId) {
        try {
            repositoryCidade.deleteById(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException((cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return repositoryCidade.findById(cidadeId).orElseThrow(() ->
                new CidadeNaoEncontradaException(cidadeId));
    }
}
