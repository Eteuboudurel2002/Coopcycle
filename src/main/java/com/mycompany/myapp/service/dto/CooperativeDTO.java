package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Cooperative} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CooperativeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String zone;

    private UtilisateurDTO idDG;

    private Set<BoutiqueDTO> boutiques = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public UtilisateurDTO getIdDG() {
        return idDG;
    }

    public void setIdDG(UtilisateurDTO idDG) {
        this.idDG = idDG;
    }

    public Set<BoutiqueDTO> getBoutiques() {
        return boutiques;
    }

    public void setBoutiques(Set<BoutiqueDTO> boutiques) {
        this.boutiques = boutiques;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CooperativeDTO)) {
            return false;
        }

        CooperativeDTO cooperativeDTO = (CooperativeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cooperativeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CooperativeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", zone='" + getZone() + "'" +
            ", idDG=" + getIdDG() +
            ", boutiques=" + getBoutiques() +
            "}";
    }
}
