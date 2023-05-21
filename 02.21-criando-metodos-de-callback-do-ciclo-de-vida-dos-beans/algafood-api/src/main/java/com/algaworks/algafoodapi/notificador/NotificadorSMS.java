package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@TipoNotificador(value = NivelUrgencia.URGENTE)
@Component
@Profile("prd")
public class NotificadorSMS implements Notificador {

    @PostConstruct
    public void init(){
        System.out.println("INICIANDO: NotificadorSMS");
    }
    @Override
    public Response notificar(Cliente cliente, String mensagem) {
        return new Response(cliente, "sms",null, mensagem);
    }

}
