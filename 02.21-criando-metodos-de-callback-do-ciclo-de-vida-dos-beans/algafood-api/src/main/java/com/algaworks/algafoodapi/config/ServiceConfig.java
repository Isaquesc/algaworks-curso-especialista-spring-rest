package com.algaworks.algafoodapi.config;

import com.algaworks.algafoodapi.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AtivacaoClienteService ativacaoClienteService() {
        return new AtivacaoClienteService();
    }
}
