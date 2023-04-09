package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.Panier;
import com.mycompany.myapp.domain.Utilisateur;
import com.mycompany.myapp.service.dto.CourseDTO;
import com.mycompany.myapp.service.dto.PanierDTO;
import com.mycompany.myapp.service.dto.UtilisateurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Course} and its DTO {@link CourseDTO}.
 */
@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {
    @Mapping(target = "idPanier", source = "idPanier", qualifiedByName = "panierId")
    @Mapping(target = "idCoursier", source = "idCoursier", qualifiedByName = "utilisateurId")
    CourseDTO toDto(Course s);

    @Named("panierId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PanierDTO toDtoPanierId(Panier panier);

    @Named("utilisateurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UtilisateurDTO toDtoUtilisateurId(Utilisateur utilisateur);
}
