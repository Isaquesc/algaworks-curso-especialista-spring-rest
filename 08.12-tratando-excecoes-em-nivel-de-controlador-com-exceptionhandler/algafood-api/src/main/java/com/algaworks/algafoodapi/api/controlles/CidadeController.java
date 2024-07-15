package com.algaworks.algafoodapi.api.controlles;

import com.algaworks.algafoodapi.api.exceptionhandler.Problem;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return ResponseEntity.ok(cidadeService.buscarOuFalhar(cidadeId));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade save(@RequestBody Cidade cidade) {
        try {
            return cidadeService.save(cidade);
        }catch (EstadoNaoEncontradoException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{cidadeId}", method = RequestMethod.PUT)
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return ResponseEntity.ok(cidadeService.save(cidadeAtual));
        }catch (EstadoNaoEncontradoException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @RequestMapping(value = {"{cidadeId}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cidadeId) {
        cidadeService.remove(cidadeId);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
        var problem = Problem.builder()
                .localDateTime(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handlerNegocioException(NegocioException e) {
        var problem = Problem.builder()
                .localDateTime(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(problem);
    }
}
