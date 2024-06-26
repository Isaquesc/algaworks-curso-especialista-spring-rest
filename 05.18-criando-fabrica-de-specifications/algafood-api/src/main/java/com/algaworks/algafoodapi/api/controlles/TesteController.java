package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.infrastucture.spec.RestauranteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.algaworks.algafoodapi.infrastucture.spec.RestauranteSpec.comFreteGratis;
import static com.algaworks.algafoodapi.infrastucture.spec.RestauranteSpec.comNomeSemelhante;

@RestController
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinha/por-nome")
    public Optional<List<Cozinha>> cozinhaByNome(@RequestParam String nome) {
        return repository.findByNome(nome);
    }

    @GetMapping("/cozinha/exists")
    public boolean cozinhaExists(@RequestParam String nome) {
        return repository.existsByNome(nome);
    }

    @GetMapping("/cozinha/contem-nome")
    public Optional<List<Cozinha>> cozinhaContainingByNome(@RequestParam String nome) {
        return repository.findByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/taxa-frete")
    public List<Restaurante> restauranteByTaxaFrete(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/nome-taxa-frete")
    public List<Restaurante> restauranteByTaxaFrete(@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaInicial, @RequestParam(required = false) BigDecimal taxaFinal) {
        return restauranteRepository.buscarPorNomeTaxaFrete(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/nome-id-cozinha")
    public List<Restaurante> restauranteByNomeByCozinhaId(@RequestParam String nome, @RequestParam Long id) {
        return restauranteRepository.consultarPorNomeXML(nome, id);
    }

    @GetMapping("/restaurantes/primeiro-nome")
    public Optional<Restaurante> restauranteFirstByNome(@RequestParam String nome) {
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/top2-nome")
    public List<Restaurante> restauranteTopByNome(@RequestParam String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/count-cozinha")
    public int restauranteCountCozinha(@RequestParam Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/restaurantes/id-cozinha")
    public Optional<List<Restaurante>> restauranteIdCozinha(@RequestParam Long cozinhaId) {
        return restauranteRepository.findByCozinhaId(cozinhaId);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis()
                .and(comNomeSemelhante(nome)));
    }

}
