package com.algaworks.algafoodapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    private Cliente cliente;
    private String tipo;
    private String smtp;
    private String msg;


}
