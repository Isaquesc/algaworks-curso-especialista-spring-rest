package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.interfaces.CidadeRepository;
import com.algaworks.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
        Cidade cidade = cidadeRepository.findById(cidadeId);

        if (cidade == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cidade);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Cidade cidade) {
        try {
            Cidade save = cidadeService.save(cidade);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(save);

        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{cidadeId}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {


        Cidade cidadeAtual = cidadeRepository.findById(cidadeId);

        if (cidadeAtual != null) {
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            cidadeAtual = cidadeService.save(cidadeAtual);
            return ResponseEntity.ok(cidadeAtual);
        }

        return ResponseEntity.notFound()
                .build();
    }

    @RequestMapping(value = {"{cidadeId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Cidade> remove(@PathVariable Long cidadeId) {
        try {
            cidadeService.remove(cidadeId);
            return ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
