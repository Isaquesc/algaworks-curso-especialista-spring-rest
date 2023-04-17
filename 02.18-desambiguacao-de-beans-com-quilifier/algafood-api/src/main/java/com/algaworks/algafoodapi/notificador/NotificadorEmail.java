package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Qualifier("NORMAL")
@Component
public class NotificadorEmail implements Notificador {

    @Override
    public Response notificar(Cliente cliente, String mensagem) {
        return new Response(cliente, "email",null, mensagem);
    }

}
