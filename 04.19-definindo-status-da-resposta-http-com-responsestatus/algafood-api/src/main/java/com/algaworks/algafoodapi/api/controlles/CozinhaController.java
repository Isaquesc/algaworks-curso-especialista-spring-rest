package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.api.model.CozinhaXMLWrapper;
import com.algaworks.algafoodapi.model.Cozinha;
import com.algaworks.algafoodapi.repository.interfaces.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/cozinhas")
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

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXMLWrapper listarXml() {
        return new CozinhaXMLWrapper(repository.findAll());
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(HttpStatus.OK)
    public Cozinha buscaId(@PathVariable Long id) {
        return repository.findById(id);
    }
}
