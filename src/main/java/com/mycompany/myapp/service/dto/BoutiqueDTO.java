package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Boutique} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BoutiqueDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String activity;

    @NotNull
    private String description;

    @NotNull
    private String address;

    private UtilisateurDTO proprio;

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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UtilisateurDTO getProprio() {
        return proprio;
    }

    public void setProprio(UtilisateurDTO proprio) {
        this.proprio = proprio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoutiqueDTO)) {
            return false;
        }

        BoutiqueDTO boutiqueDTO = (BoutiqueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, boutiqueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BoutiqueDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", activity='" + getActivity() + "'" +
            ", description='" + getDescription() + "'" +
            ", address='" + getAddress() + "'" +
            ", proprio=" + getProprio() +
            "}";
    }
}
