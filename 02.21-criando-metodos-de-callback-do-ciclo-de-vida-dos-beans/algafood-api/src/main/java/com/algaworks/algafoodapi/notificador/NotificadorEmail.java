package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@TipoNotificador(value = NivelUrgencia.NORMAL)
@Component
@Profile("prd")
public class NotificadorEmail implements Notificador {

    @Override
    public Response notificar(Cliente cliente, String mensagem) {
        return new Response(cliente, "email",null, mensagem);
    }

}
