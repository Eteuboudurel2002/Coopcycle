package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Panier;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface PanierRepositoryWithBagRelationships {
    Optional<Panier> fetchBagRelationships(Optional<Panier> panier);

    List<Panier> fetchBagRelationships(List<Panier> paniers);

    Page<Panier> fetchBagRelationships(Page<Panier> paniers);
}
