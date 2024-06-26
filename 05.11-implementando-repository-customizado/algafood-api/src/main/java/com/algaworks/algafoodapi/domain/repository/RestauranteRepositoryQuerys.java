package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepositoryQuerys {

    List<Restaurante> buscarPorNomeTaxaFrete2(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
