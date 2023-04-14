package com.algaworks.algafoodapi.config;

import com.algaworks.algafoodapi.notificador.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificadorConfig {

    @Bean
    public NotificadorEmail notificadorEmail(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algaemail.com.br");
        notificadorEmail.setCaixaAlta(false);

        return notificadorEmail;
    }

}
