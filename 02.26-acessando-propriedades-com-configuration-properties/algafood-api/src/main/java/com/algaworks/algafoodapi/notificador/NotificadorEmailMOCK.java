package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.NotificadorEmailProperties;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@TipoNotificador(value = NivelUrgencia.URGENTE)
@Component
@Profile("dev")
public class NotificadorEmailMOCK implements Notificador {


    @Autowired
    private NotificadorEmailProperties properties;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("host: " + properties.getHostServidor());
        System.out.println("porta: " + properties.getPortaServidor());

        System.out.println(new Response(cliente, "email",null, "MOCK: " + mensagem));
    }

}
