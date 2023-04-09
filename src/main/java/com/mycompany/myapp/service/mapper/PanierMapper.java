package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Panier;
import com.mycompany.myapp.domain.Produit;
import com.mycompany.myapp.domain.Utilisateur;
import com.mycompany.myapp.service.dto.PanierDTO;
import com.mycompany.myapp.service.dto.ProduitDTO;
import com.mycompany.myapp.service.dto.UtilisateurDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Panier} and its DTO {@link PanierDTO}.
 */
@Mapper(componentModel = "spring")
public interface PanierMapper extends EntityMapper<PanierDTO, Panier> {
    @Mapping(target = "produits", source = "produits", qualifiedByName = "produitIdSet")
    @Mapping(target = "idClient", source = "idClient", qualifiedByName = "utilisateurId")
    PanierDTO toDto(Panier s);

    @Mapping(target = "removeProduit", ignore = true)
    Panier toEntity(PanierDTO panierDTO);

    @Named("produitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProduitDTO toDtoProduitId(Produit produit);

    @Named("produitIdSet")
    default Set<ProduitDTO> toDtoProduitIdSet(Set<Produit> produit) {
        return produit.stream().map(this::toDtoProduitId).collect(Collectors.toSet());
    }

    @Named("utilisateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UtilisateurDTO toDtoUtilisateurId(Utilisateur utilisateur);
}
