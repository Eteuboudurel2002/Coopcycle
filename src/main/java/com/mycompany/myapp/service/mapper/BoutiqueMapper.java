package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Boutique;
import com.mycompany.myapp.domain.Utilisateur;
import com.mycompany.myapp.service.dto.BoutiqueDTO;
import com.mycompany.myapp.service.dto.UtilisateurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Boutique} and its DTO {@link BoutiqueDTO}.
 */
@Mapper(componentModel = "spring")
public interface BoutiqueMapper extends EntityMapper<BoutiqueDTO, Boutique> {
    @Mapping(target = "proprio", source = "proprio", qualifiedByName = "utilisateurId")
    BoutiqueDTO toDto(Boutique s);

    @Named("utilisateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UtilisateurDTO toDtoUtilisateurId(Utilisateur utilisateur);
}
