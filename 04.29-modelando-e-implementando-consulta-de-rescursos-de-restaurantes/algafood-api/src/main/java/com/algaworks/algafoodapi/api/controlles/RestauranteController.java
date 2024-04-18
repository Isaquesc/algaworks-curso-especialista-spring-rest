package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteService restauranteservice;

    @Autowired
    public RestauranteController(RestauranteService restauranteservice) {
        this.restauranteservice = restauranteservice;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Restaurante>> findAll() {
        return ResponseEntity.ok(restauranteservice.findAll());
    }

    @RequestMapping(value = "/{restauranteId}")
    public ResponseEntity<?> findByID(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteservice.findById(restauranteId);

        if (restaurante == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(restaurante);
    }
}
