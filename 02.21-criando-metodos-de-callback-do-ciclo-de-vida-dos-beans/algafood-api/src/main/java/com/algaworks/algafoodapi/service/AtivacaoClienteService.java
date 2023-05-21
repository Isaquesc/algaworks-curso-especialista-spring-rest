package com.algaworks.algafoodapi.service;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import com.algaworks.algafoodapi.notificador.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @TipoNotificador(value = NivelUrgencia.NORMAL)
    @Autowired
    private Notificador notificador;

    //    @PostConstruct
    public void init() {
        System.out.println("INICIANDO: AtivacaoClienteService");
    }

    //    @PreDestroy
    public void destroy() {
        System.out.println("FINALIZANDO: AtivacaoClienteService");
    }

    public Response ativar(Cliente cliente) {
        cliente.ativar();

        return notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
    }

}
