package com.algaworks.algafoodapi.service;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import com.algaworks.algafoodapi.notificador.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AtivacaoClienteService {

    @Autowired
    private Notificador notificador;

//    @Autowired
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//        System.out.println("Contrutor Ativacao Cliente Service: " + notificador);
//    }

    public AtivacaoClienteService(){
        System.out.println("Segundo Construtor");
    }

    public Response ativar(Cliente cliente) {
        cliente.ativar();
        return notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
    }

//    @Autowired
//    public void setNotificador(Notificador notificador){
//        this.notificador = notificador;
//    }

}
