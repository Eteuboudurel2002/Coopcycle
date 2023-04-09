package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Boutique;
import com.mycompany.myapp.domain.Produit;
import com.mycompany.myapp.service.dto.BoutiqueDTO;
import com.mycompany.myapp.service.dto.ProduitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {
    @Mapping(target = "idboutique", source = "idboutique", qualifiedByName = "boutiqueId")
    ProduitDTO toDto(Produit s);

    @Named("boutiqueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BoutiqueDTO toDtoBoutiqueId(Boutique boutique);
}
