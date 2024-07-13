package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
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
        var restaurante = restauranteservice.buscarOuFalhar(restauranteId);
        return ResponseEntity.ok(restaurante);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante save(@RequestBody Restaurante restaurante) {
        try {
            return restauranteservice.save(restaurante);
        }catch (EntidadeNaoEnconstradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{restauranteId}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {

        var restauranteAtual = restauranteservice.buscarOuFalhar(restauranteId);
        BeanUtils.copyProperties(restaurante, restauranteAtual,
                "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try {
            return ResponseEntity.ok()
                    .body(restauranteservice.save(restauranteAtual));
        } catch (EntidadeNaoEnconstradaException ex) {
            throw new EntidadeNaoEnconstradaException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{restauranteId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {
        var restauranteAtual = restauranteservice.buscarOuFalhar(restauranteId);
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
