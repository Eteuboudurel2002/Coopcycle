package com.mycompany.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Course} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CourseDTO implements Serializable {

    private Long id;

    /**
     * Objet créer lors de la validation d'un panier
     */
    @NotNull
    @Schema(description = "Objet créer lors de la validation d'un panier", required = true)
    private String echeance;

    @NotNull
    private LocalDate dateCreation;

    @NotNull
    private String statut;

    private PanierDTO idPanier;

    private UtilisateurDTO idCoursier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEcheance() {
        return echeance;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public PanierDTO getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(PanierDTO idPanier) {
        this.idPanier = idPanier;
    }

    public UtilisateurDTO getIdCoursier() {
        return idCoursier;
    }

    public void setIdCoursier(UtilisateurDTO idCoursier) {
        this.idCoursier = idCoursier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseDTO)) {
            return false;
        }

        CourseDTO courseDTO = (CourseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, courseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CourseDTO{" +
            "id=" + getId() +
            ", echeance='" + getEcheance() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", statut='" + getStatut() + "'" +
            ", idPanier=" + getIdPanier() +
            ", idCoursier=" + getIdCoursier() +
            "}";
    }
}
