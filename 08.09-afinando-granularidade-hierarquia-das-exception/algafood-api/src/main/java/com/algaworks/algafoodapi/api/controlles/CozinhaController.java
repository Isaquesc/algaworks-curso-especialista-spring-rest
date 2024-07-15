package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.api.model.CozinhaXMLWrapper;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    private final CozinhaRepository cozinhaRepository;
    private final CozinhaService cozinhaService;

    @Autowired
    public CozinhaController(CozinhaRepository cozinhaRepository, CozinhaService cozinhaService) {
        this.cozinhaRepository = cozinhaRepository;
        this.cozinhaService = cozinhaService;
    }

    @RequestMapping(method = GET)
    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXMLWrapper findAllXML() {
        return new CozinhaXMLWrapper(cozinhaRepository.findAll());
    }

    @RequestMapping(value = "{cozinhaId}", method = GET)
    public ResponseEntity<Cozinha> findByID(@PathVariable Long cozinhaId) {
        return ResponseEntity.ok(cozinhaService.buscarOuFalhar(cozinhaId));
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save(@RequestBody Cozinha cozinha) {
        return cozinhaService.save(cozinha);
    }

    @RequestMapping(value = "{cozinhaId}", method = PUT)
    public ResponseEntity<Cozinha> update(@PathVariable Long cozinhaId,
                                          @RequestBody Cozinha cozinha) {
        var cozinhaAtualizada = cozinhaService.buscarOuFalhar(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaAtualizada, "id");

        return ResponseEntity.ok(cozinhaService.save(cozinhaAtualizada));
    }

    @RequestMapping(value = {"{cozinhaId}"}, method = DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cozinhaId) {
        cozinhaService.remove(cozinhaId);
    }
}

