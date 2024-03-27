package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.model.Cozinha;
import com.algaworks.algafoodapi.repository.interfaces.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    private final CozinhaRepository repository;

    @Autowired
    public CozinhaController(CozinhaRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = GET)
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Cozinha buscaId(@PathVariable Long id){
        return repository.findById(id);
    }
}
