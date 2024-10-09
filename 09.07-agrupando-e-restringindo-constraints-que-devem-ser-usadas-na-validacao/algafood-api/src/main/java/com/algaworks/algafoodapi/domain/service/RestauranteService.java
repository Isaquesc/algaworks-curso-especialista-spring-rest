package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private final CozinhaService cozinhaService;
    private RestauranteRepository restauranteRepository;

    private CozinhaRepository cozinhaRepository;

    @Autowired
    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository, CozinhaService cozinhaService) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
    }

    public Restaurante save(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        var cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
