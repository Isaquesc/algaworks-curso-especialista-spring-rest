package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeService cidadeService;

    private CidadeRepository cidadeRepository;

    @Autowired
    public CidadeController(CidadeService cidadeService, CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cidade>> findAll() {
        return ResponseEntity.ok(cidadeRepository.findAll());
    }

    @RequestMapping(value = "/{cidadeId}", method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@PathVariable Long cidadeId) {
        return ResponseEntity.ok(cidadeService.buscarOuFalhar(cidadeId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Cidade cidade) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(cidadeService.save(cidade));
    }

    @RequestMapping(value = "/{cidadeId}", method = RequestMethod.PUT)
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return ResponseEntity.ok(cidadeService.save(cidadeAtual));
    }

    @RequestMapping(value = {"{cidadeId}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cidadeId) {
        cidadeService.remove(cidadeId);
    }
}
