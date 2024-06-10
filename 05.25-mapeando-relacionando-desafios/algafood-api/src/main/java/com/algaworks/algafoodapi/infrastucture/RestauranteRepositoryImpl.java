package com.algaworks.algafoodapi.infrastucture;


import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQuerys;
import com.algaworks.algafoodapi.infrastucture.spec.RestauranteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuerys {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

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

    @Override
    public List<Restaurante> comFreteGratis(String nome) {
        return restauranteRepository.findAll(RestauranteSpec.comFreteGratis()
                .and(RestauranteSpec.comNomeSemelhante(nome)));
    }


}
