package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.stereotype.Component;

@Component
public class NotificadorSMS implements Notificador {

    @Override
    public Response notificar(Cliente cliente, String mensagem) {
        return new Response(cliente, "sms",null, mensagem);
    }

}
