package com.algaworks.algafoodapi.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problem {

    private LocalDateTime localDateTime;
    private String mensagem;
}
