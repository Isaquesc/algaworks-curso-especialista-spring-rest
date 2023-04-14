package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.stereotype.Component;

public class NotificadorEmail implements Notificador {

    private String hostServidorSmtp;
    private boolean caixaAlta;

    public NotificadorEmail(String hostServidorSmtp) {
        this.hostServidorSmtp = hostServidorSmtp;
        System.out.println("Contrutor NotificadorEmail chamado");
    }

    @Override
    public Response notificar(Cliente cliente, String mensagem) {

        if (caixaAlta)
            mensagem = mensagem.toUpperCase();

        return new Response(cliente, "email",this.hostServidorSmtp, mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
