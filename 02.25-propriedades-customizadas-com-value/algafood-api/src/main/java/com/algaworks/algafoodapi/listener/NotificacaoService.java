package com.algaworks.algafoodapi.listener;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.notificador.Notificador;
import com.algaworks.algafoodapi.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

@Component
public class NotificacaoService {

    @TipoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    private Notificador notificador;

    @EventListener
    @Order(value = 1)
    public void clienteAtivadoListener(ClienteAtivadoEvent event){
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo");
    }
}
