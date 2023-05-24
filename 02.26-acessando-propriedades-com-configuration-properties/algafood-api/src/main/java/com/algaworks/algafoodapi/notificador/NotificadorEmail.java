package com.algaworks.algafoodapi.notificador;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import com.algaworks.algafoodapi.model.Cliente;
import com.algaworks.algafoodapi.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

@TipoNotificador(value = NivelUrgencia.URGENTE)
@Component
@Profile("prd")
public class NotificadorEmail implements Notificador {

    @Value("${notificado.email.host-servidor}")
    private String host;

    @Value("${notificado.email.porta-servidor}")
    private Integer porta;

    @Autowired
    Environment env;

    @Override
    public void notificar(Cliente cliente, String mensagem) {

        Optional<String> first = Arrays.stream(env.getActiveProfiles()).findFirst();
        first.ifPresent(System.out::println);

        System.out.println("host: " + host);
        System.out.println("porta: " + porta);

        System.out.println(new Response(cliente, "email",null, mensagem ));
    }
}
