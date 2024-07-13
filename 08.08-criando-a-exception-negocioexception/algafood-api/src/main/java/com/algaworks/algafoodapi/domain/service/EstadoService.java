package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    public static final String MSG_ESTADO_NAO_ENCOSTRADO = "Não existe um cadastro de estado com código %d";

    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";

    private EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remove(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEnconstradaException(
                    String.format(MSG_ESTADO_NAO_ENCOSTRADO, estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEnconstradaException(
                        String.format(MSG_ESTADO_NAO_ENCOSTRADO, estadoId)));
    }
}
