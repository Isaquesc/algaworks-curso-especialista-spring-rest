package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;

public interface Notificador {
    Response notificar(Cliente cliente, String mensagem);
}
