package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import com.algaworks.algafoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    private final EstadoRepository estadoRepository;

    private final EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoRepository estadoRepository, EstadoService estadoService) {
        this.estadoService = estadoService;
        this.estadoRepository = estadoRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Estado>> findAll() {
        return ResponseEntity.ok(estadoRepository.findAll());
    }

    @RequestMapping(value = "/{estadoId}", method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@PathVariable Long estadoId) {
        var estado = estadoService.buscarOuFalhar(estadoId);
        return ResponseEntity.ok(estado);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Estado save(@RequestBody Estado estado) {
        try {
            return estadoService.save(estado);
        }catch (CidadeNaoEncontradaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{estadoId}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        var estadoAtual = estadoService.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");

        try {
            return ResponseEntity.ok(estadoService.save(estadoAtual));
        } catch (CidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @RequestMapping(value = {"{estadoId}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long estadoId) {
        estadoService.remove(estadoId);
    }
}

