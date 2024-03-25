package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.jpa.CadastroCozinha;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CadastroCozinhaApiApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CadastroCozinhaApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        applicationContext
                .getBean(CadastroCozinha.class)
                .findAll()
                .forEach(System.out::println);
    }

}
