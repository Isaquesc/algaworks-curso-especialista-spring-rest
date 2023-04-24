package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
