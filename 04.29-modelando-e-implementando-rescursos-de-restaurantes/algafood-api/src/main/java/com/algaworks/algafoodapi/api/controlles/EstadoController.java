package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEnconstradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.interfaces.EstadoRepository;
import com.algaworks.algafoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        Estado estado = estadoRepository.findById(estadoId);

        if (estado == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(estado);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Estado estado) {
        try {
            Estado save = estadoService.save(estado);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(save);

        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{estadoId}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = estadoRepository.findById(estadoId);

        if (estadoAtual != null) {
            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual = estadoService.save(estadoAtual);
            return ResponseEntity.ok(estadoAtual);
        }

        return ResponseEntity.notFound()
                .build();
    }

    @RequestMapping(value = {"{estadoId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Estado> remove(@PathVariable Long estadoId) {
        try {
            estadoService.remove(estadoId);
            return ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEnconstradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
