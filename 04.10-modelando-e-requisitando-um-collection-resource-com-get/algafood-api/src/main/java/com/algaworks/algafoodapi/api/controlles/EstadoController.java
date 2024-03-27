package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.model.Estado;
import com.algaworks.algafoodapi.repository.interfaces.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoRepository repository;

    @Autowired
    public EstadoController(EstadoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Estado> listar() {
        return repository.findAll();
    }
}
