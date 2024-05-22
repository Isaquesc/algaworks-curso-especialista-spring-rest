package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.api.model.CozinhaXMLWrapper;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.interfaces.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {


    private final CozinhaRepository repository;
    private final CozinhaService service;

    @Autowired
    public CozinhaController(CozinhaRepository repository, CozinhaService service) {
        this.repository = repository;
        this.service = service;
    }

    @RequestMapping(method = GET)
    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXMLWrapper findAllXML() {
        return new CozinhaXMLWrapper(repository.findAll());
    }

    @RequestMapping(value = "{cozinhaId}", method = GET)
    public ResponseEntity<Cozinha> findByID(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = repository.findById(cozinhaId);
        if (cozinha.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cozinha.get());
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save(@RequestBody Cozinha cozinha) {
        return service.save(cozinha);
    }

    @RequestMapping(value = "{cozinhaId}", method = PUT)
    public ResponseEntity<Cozinha> update(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtualizada = repository.findById(cozinhaId);

        if (cozinhaAtualizada.isEmpty())
            return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(cozinha, cozinhaAtualizada.get(), "id");
        return ResponseEntity.ok(service.save(cozinhaAtualizada.get()));
    }

    @RequestMapping(value = {"{cozinhaId}"}, method = DELETE)
    public ResponseEntity<Cozinha> remove(@PathVariable Long cozinhaId) {
        try {
            service.remove(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

