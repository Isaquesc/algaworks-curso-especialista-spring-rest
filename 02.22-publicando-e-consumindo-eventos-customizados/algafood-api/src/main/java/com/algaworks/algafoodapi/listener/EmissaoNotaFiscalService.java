package com.algaworks.algafoodapi.listener;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.notificador.Notificador;
import com.algaworks.algafoodapi.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    @Order(value = 2)
    public void clienteAtivadoListener(ClienteAtivadoEvent event){
        System.out.println("Emitindo nota fiscal para cliente " + event.getCliente().getNome());
    }
}
