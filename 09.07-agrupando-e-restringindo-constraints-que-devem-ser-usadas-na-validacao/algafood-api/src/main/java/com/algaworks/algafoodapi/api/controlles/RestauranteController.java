package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Grupo;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import com.algaworks.algafoodapi.infrastucture.Groups;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Restaurante>> findAll() throws IllegalAccessException {
        return ResponseEntity.ok(restauranteRepository.findAll());
    }

    @RequestMapping(value = "/{restauranteId}", method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@PathVariable Long restauranteId) {
        var restaurante = restauranteservice.buscarOuFalhar(restauranteId);
        return ResponseEntity.ok(restaurante);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante save(@RequestBody @Validated(Groups.CadastroRestaurante.class) Restaurante restaurante) {
        try {
            return restauranteservice.save(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
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
        } catch (CozinhaNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{restauranteId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        var restauranteAtual = restauranteservice.buscarOuFalhar(restauranteId);
        merge(campos, restauranteAtual, request);

        return atualizar(restauranteId, restauranteAtual);
    }

    private static void merge(Map<String, Object> campos, Restaurante restauranteDestino, HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            Restaurante restauranteOrigem = mapper.convertValue(campos, Restaurante.class);

            campos.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValorPropriedade = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValorPropriedade);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }
}
