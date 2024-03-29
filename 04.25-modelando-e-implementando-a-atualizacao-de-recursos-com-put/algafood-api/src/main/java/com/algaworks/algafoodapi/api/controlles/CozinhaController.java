package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.api.model.CozinhaXMLWrapper;
import com.algaworks.algafoodapi.model.Cozinha;
import com.algaworks.algafoodapi.repository.interfaces.CozinhaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;
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

    @RequestMapping(value = "/{cozinhaId" +
            "}", method = GET)
    public ResponseEntity<Cozinha> buscaId(@PathVariable Long cozinhaId
    ) {
        Cozinha cozinha = repository.findById(cozinhaId
        );
        if (cozinha != null)
            return ResponseEntity.ok(cozinha);

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return repository.save(cozinha);
    }


    @RequestMapping(value = "{cozinhaId}", method = PUT)
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtualizada = repository.findById(cozinhaId);
            if (cozinhaAtualizada != null) {
                BeanUtils.copyProperties(cozinha, cozinhaAtualizada, "id");

                return ResponseEntity.ok(repository.save(cozinhaAtualizada));
            }
        return ResponseEntity.notFound().build();
    }
}
