package com.algaworks.algafoodapi.infrastucture;


import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQuerys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuerys {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> buscarPorNomeTaxaFrete(String nome,
                                                    BigDecimal taxaInicial,
                                                    BigDecimal taxaFinal) {

        var jpql = new StringBuilder().append("from Restaurante where 0 = 0 ");
        var parametos = new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametos.put("nome", "%" + nome + "%");
        }

        if (taxaInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametos.put("taxaInicial", taxaInicial);
        }

        if (taxaFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal ");
            parametos.put("taxaFinal", taxaFinal);
        }

        var query = manager.createQuery(jpql.toString(), Restaurante.class);
        parametos.forEach(query::setParameter);

        return query.getResultList();
    }
}
