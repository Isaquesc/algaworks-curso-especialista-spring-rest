package com.algaworks.algafoodapi.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
}


