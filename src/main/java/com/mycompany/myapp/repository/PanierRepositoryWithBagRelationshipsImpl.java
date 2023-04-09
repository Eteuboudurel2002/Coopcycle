package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Panier;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class PanierRepositoryWithBagRelationshipsImpl implements PanierRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Panier> fetchBagRelationships(Optional<Panier> panier) {
        return panier.map(this::fetchProduits);
    }

    @Override
    public Page<Panier> fetchBagRelationships(Page<Panier> paniers) {
        return new PageImpl<>(fetchBagRelationships(paniers.getContent()), paniers.getPageable(), paniers.getTotalElements());
    }

    @Override
    public List<Panier> fetchBagRelationships(List<Panier> paniers) {
        return Optional.of(paniers).map(this::fetchProduits).orElse(Collections.emptyList());
    }

    Panier fetchProduits(Panier result) {
        return entityManager
            .createQuery("select panier from Panier panier left join fetch panier.produits where panier is :panier", Panier.class)
            .setParameter("panier", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Panier> fetchProduits(List<Panier> paniers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, paniers.size()).forEach(index -> order.put(paniers.get(index).getId(), index));
        List<Panier> result = entityManager
            .createQuery("select distinct panier from Panier panier left join fetch panier.produits where panier in :paniers", Panier.class)
            .setParameter("paniers", paniers)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
