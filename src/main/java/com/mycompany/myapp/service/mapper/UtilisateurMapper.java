package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Cooperative;
import com.mycompany.myapp.domain.Utilisateur;
import com.mycompany.myapp.service.dto.CooperativeDTO;
import com.mycompany.myapp.service.dto.UtilisateurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Utilisateur} and its DTO {@link UtilisateurDTO}.
 */
@Mapper(componentModel = "spring")
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {
    @Mapping(target = "idCoop", source = "idCoop", qualifiedByName = "cooperativeId")
    UtilisateurDTO toDto(Utilisateur s);

    @Named("cooperativeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativeDTO toDtoCooperativeId(Cooperative cooperative);
}
