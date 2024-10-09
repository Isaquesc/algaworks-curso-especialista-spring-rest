package com.algaworks.algafoodapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradaException(Long id) {
        super(String.format("Não existe um cadastro de cozinha com código %d", id));
    }
}
