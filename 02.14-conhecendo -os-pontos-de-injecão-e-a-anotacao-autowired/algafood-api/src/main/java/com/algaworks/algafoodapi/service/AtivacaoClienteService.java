package com.algaworks.algafoodapi.service;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import com.algaworks.algafoodapi.notificador.Notificador;
import org.springframework.beans.factory.annotation.Autowired;

public class AtivacaoClienteService {

    Notificador notificador;

    @Autowired
    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("Contrutor AtivacaoClienteService: " + notificador);
    }

    public Response ativar(Cliente cliente) {
        cliente.ativar();
        return notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
    }

}
