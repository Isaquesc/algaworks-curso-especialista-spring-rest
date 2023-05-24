package com.algaworks.algafoodapi.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("notificado.email")
public class NotificadorEmailProperties {

    private String hostServidor;
    private Integer portaServidor;

}

