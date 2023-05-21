package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@TipoNotificador(value = NivelUrgencia.URGENTE)
@Component
@Profile("dev")
public class NotificadorEmailMOCK implements Notificador {

    @PostConstruct
    public void init(){
        System.out.println("INICIANDO: NotificadorEmailMOCK");
    }

    @Override
    public Response notificar(Cliente cliente, String mensagem) {
        return new Response(cliente, "email",null, "MOCK: " + mensagem);
    }

}
