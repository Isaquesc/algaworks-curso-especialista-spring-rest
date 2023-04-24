package com.algaworks.algafoodapi.service;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import com.algaworks.algafoodapi.notificador.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        //emitindo um evento que o cliente esta ativo
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));

    }

}
