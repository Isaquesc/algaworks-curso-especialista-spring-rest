package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.interfaces.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.interfaces.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinha/por-nome")
    public Optional<List<Cozinha>> cozinhaByNome(@RequestParam String nome){
        return repository.findByNome(nome);
    }

    @GetMapping("/cozinha/contem-nome")
    public Optional<List<Cozinha>> cozinhaContainingByNome(@RequestParam String nome){
        return repository.findByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restauranteByTaxaFrete(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nomeRestaurante-cozinhaId")
    public List<Restaurante> restauranteByNomeByCozinhaId(@RequestParam String nome, @RequestParam Long id){
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);
    }

}
