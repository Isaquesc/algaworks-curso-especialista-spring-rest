package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@TipoNotificador(value = NivelUrgencia.URGENTE)
@Component
@Profile("prd")
public class NotificadorEmail implements Notificador {

    @Value("${notificado.email.host-servidor}")
    private String host;

    @Value("${notificado.email.porta-servidor}")
    private Integer porta;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("host: " + host);
        System.out.println("host: " + porta);

        System.out.println(new Response(cliente, "email",null, mensagem ));
    }

}
