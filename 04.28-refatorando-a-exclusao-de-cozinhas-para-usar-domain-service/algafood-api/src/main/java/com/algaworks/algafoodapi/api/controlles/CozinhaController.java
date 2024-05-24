package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.api.model.CozinhaXMLWrapper;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    private final CozinhaRepository repository;

    private final CadastroCozinhaService service;

    @Autowired
    public CozinhaController(CozinhaRepository repository, CadastroCozinhaService service) {
        this.repository = repository;
        this.service = service;
    }

    @RequestMapping(method = GET)
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXMLWrapper listarXml() {
        return new CozinhaXMLWrapper(repository.findAll());
    }

    @RequestMapping(value = "{cozinhaId}", method = GET)
    public ResponseEntity<Cozinha> buscaId(@PathVariable Long cozinhaId) {
        Cozinha cozinha = repository.findById(cozinhaId);
        if (cozinha == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cozinha);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return service.save(cozinha);
    }

    @RequestMapping(value = "{cozinhaId}", method = PUT)
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtualizada = repository.findById(cozinhaId);
        if (cozinhaAtualizada == null)
            return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(cozinha, cozinhaAtualizada, "id");
        return ResponseEntity.ok(service.save(cozinhaAtualizada));
    }

    @RequestMapping(value = {"{cozinhaId}"}, method = DELETE)
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try {
            service.remove(cozinhaId);
            return ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

