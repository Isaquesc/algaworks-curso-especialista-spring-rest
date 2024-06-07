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

        Predicate nomePredicate = criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        Predicate taxaInicialPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("TaxaFrete"), taxaInicial);
        Predicate taxaFinalPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("TaxaFrete"), taxaFinal);

        CriteriaQuery<Restaurante> where = criteriaQuery.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);
        return manager.createQuery(criteriaQuery).getResultList();
    }
}
