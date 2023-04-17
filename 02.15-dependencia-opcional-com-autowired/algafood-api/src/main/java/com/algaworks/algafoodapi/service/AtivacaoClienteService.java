package com.algaworks.algafoodapi.service;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import com.algaworks.algafoodapi.notificador.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AtivacaoClienteService {

    @Autowired(required = false)
    private Notificador notificador;

    public AtivacaoClienteService() {
        System.out.println("Segundo Construtor");
    }

    public Response ativar(Cliente cliente) {
        cliente.ativar();

        if (notificador != null) {
            return notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
        } else {
            return new Response(cliente,"null","null", "Não existe notificador, mas cliente foi ativado");
        }
    }

}
