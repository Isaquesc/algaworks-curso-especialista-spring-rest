package com.algaworks.algafoodapi.infrastucture;


import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQuerys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuerys {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Restaurante> buscarPorNomeTaxaFrete(String nome,
                                                       BigDecimal taxaInicial,
                                                       BigDecimal taxaFinal) {
        var jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaInicial)
                .setParameter("taxaFinal", taxaFinal)
                .getResultList();
    }
}
