package com.algaworks.algafoodapi.api.exceptionhandler;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        var problem = getProblem(ex);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handlerNegocioException(NegocioException ex, WebRequest request) {
        var problem = getProblem(ex);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerNegocioException(EntidadeEmUsoException ex, WebRequest request) {
        var problem = getProblem(ex);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .localDateTime(LocalDateTime.now())
                    .mensagem(ex.getMessage())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .localDateTime(LocalDateTime.now())
                    .mensagem((String) body)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem getProblem(Exception ex) {
        var problem = Problem.builder()
                .localDateTime(LocalDateTime.now())
                .mensagem(ex.getMessage())
                .build();

        return problem;
    }
}
