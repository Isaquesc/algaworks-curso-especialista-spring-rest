package com.algaworks.algafoodapi.anotacao;

import com.algaworks.algafoodapi.enumerados.NivelUrgencia;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoNotificador {

    NivelUrgencia value();
}
