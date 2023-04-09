package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Boutique;
import com.mycompany.myapp.domain.Cooperative;
import com.mycompany.myapp.domain.Utilisateur;
import com.mycompany.myapp.service.dto.BoutiqueDTO;
import com.mycompany.myapp.service.dto.CooperativeDTO;
import com.mycompany.myapp.service.dto.UtilisateurDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cooperative} and its DTO {@link CooperativeDTO}.
 */
@Mapper(componentModel = "spring")
public interface CooperativeMapper extends EntityMapper<CooperativeDTO, Cooperative> {
    @Mapping(target = "idDG", source = "idDG", qualifiedByName = "utilisateurId")
    @Mapping(target = "boutiques", source = "boutiques", qualifiedByName = "boutiqueIdSet")
    CooperativeDTO toDto(Cooperative s);

    @Mapping(target = "removeBoutique", ignore = true)
    Cooperative toEntity(CooperativeDTO cooperativeDTO);

    @Named("utilisateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UtilisateurDTO toDtoUtilisateurId(Utilisateur utilisateur);

    @Named("boutiqueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BoutiqueDTO toDtoBoutiqueId(Boutique boutique);

    @Named("boutiqueIdSet")
    default Set<BoutiqueDTO> toDtoBoutiqueIdSet(Set<Boutique> boutique) {
        return boutique.stream().map(this::toDtoBoutiqueId).collect(Collectors.toSet());
    }
}
