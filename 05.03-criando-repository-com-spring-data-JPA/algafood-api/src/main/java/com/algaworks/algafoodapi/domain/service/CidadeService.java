package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.interfaces.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.interfaces.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    private CidadeRepository repositoryCidade;
    private EstadoRepository estadoRepository;

    @Autowired
    public CidadeService(CidadeRepository repositoryCidade, EstadoRepository estadoRepository) {
        this.repositoryCidade = repositoryCidade;
        this.estadoRepository = estadoRepository;
    }

    public Cidade save(Cidade cidade) {
        Estado estado = estadoRepository.findById(cidade.getEstado().getId()).orElse(null);

        if (estado == null) {
            throw new EntidadeNaoEnconstradaException(
                    String.format("Não existe um cadastro de estado com código %d", cidade.getEstado().getId()));
        }
        cidade.setEstado(estado);
        return repositoryCidade.save(cidade);
    }

    public void remove(Long cidadeId) {
        try {
            if (!repositoryCidade.existsById(cidadeId))
                throw new EntidadeNaoEnconstradaException(
                        String.format("Não existe um cadastro de cidade com código %d", cidadeId));

            repositoryCidade.deleteById(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }
}
