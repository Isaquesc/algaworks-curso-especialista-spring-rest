package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.stereotype.Component;

//@Component
public class NotificadorEmail implements Notificador {

    @Override
    public Response notificar(Cliente cliente, String mensagem) {
        return new Response(cliente, "email",null, mensagem);
    }

}
