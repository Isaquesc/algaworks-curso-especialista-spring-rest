package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteService restauranteservice;

    private RestauranteRepository restauranteRepository;


    @Autowired
    public RestauranteController(RestauranteService restauranteservice, RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteservice = restauranteservice;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Restaurante>> findAll() {
        return ResponseEntity.ok(restauranteRepository.findAll());
    }

    @RequestMapping(value = "/{restauranteId}", method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if (restaurante.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(restaurante);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Restaurante restaurante) {
        try {
            Restaurante save = restauranteservice.save(restaurante);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(save);

        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{restauranteId}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {
        try {

            Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);

            if (restauranteAtual != null) {
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamentos");

                restauranteAtual = restauranteservice.save(restauranteAtual);
                return ResponseEntity.ok()
                        .body(restauranteAtual);
            }

            return ResponseEntity.notFound()
                    .build();

        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{restauranteId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
    }

    private static void merge(Map<String, Object> campos, Restaurante restauranteDestino) {
        ObjectMapper mapper = new ObjectMapper();

        Restaurante restauranteOrigem = mapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValorPropriedade = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValorPropriedade);
        });
    }
}
