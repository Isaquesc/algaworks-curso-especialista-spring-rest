package com.algaworks.algafoodapi.service;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import com.algaworks.algafoodapi.notificador.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @Qualifier("URGENTE")
    @Autowired
    private Notificador notificador;

    public Response ativar(Cliente cliente) {
        cliente.ativar();

      return notificador.notificar(cliente,"Seu cadastro no sistema está ativo");
    }

}
