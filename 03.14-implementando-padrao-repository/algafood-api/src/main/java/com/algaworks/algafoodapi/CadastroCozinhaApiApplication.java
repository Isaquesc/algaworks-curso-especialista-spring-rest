package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.jpa.CadastroCozinha;
import com.algaworks.algafoodapi.model.Cozinha;
import com.algaworks.algafoodapi.repository.CozinhaRepositoryImpl;
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

        CozinhaRepositoryImpl repository = applicationContext.getBean(CozinhaRepositoryImpl.class);

//        CADASTRAR COZINHA
        Cozinha primeiraCozinha = new Cozinha();
        primeiraCozinha.setNome("Coreana");

        repository.save(primeiraCozinha);

        Cozinha segundaCozinha = new Cozinha();
        segundaCozinha.setNome("Mexicana");

        repository.save(segundaCozinha);

//         BUSCAS TODAS COZINHAS
        repository.findAll()
                .forEach(System.out::println);

//         BUSCAS POR ID
        Cozinha cozinha = repository.findById(3L);
        System.out.println(cozinha);

//         ATUALIZANDO COZINHA
        Cozinha atualizandoCozinha = new Cozinha();
        atualizandoCozinha.setId(4L);
        atualizandoCozinha.setNome("Americana");

        System.out.println(repository
                .save(atualizandoCozinha));

//          REMOVENDO
        Cozinha removerCozinha = new Cozinha();
        removerCozinha.setId(1L);
        repository.remove(removerCozinha);
    }
}
