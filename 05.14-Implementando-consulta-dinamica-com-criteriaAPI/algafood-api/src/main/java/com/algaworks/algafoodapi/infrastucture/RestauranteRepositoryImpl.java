package com.algaworks.algafoodapi.infrastucture;


import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQuerys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
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

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);

        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaInicial != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
        }

        if (taxaFinal != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return manager.createQuery(criteriaQuery).getResultList();
    }
}
