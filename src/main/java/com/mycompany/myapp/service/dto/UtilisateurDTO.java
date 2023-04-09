package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Utilisateur} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UtilisateurDTO implements Serializable {

    private Long id;

    /**
     * Aucune clé primaire (id) n'est définie pour les relations\nen raison de la génération automaitque des clés primaires\ndes relations par Jhipster
     */
    @NotNull
    @Schema(
        description = "Aucune clé primaire (id) n'est définie pour les relations\nen raison de la génération automaitque des clés primaires\ndes relations par Jhipster",
        required = true
    )
    private String name;

    @NotNull
    @Min(value = 18)
    @Max(value = 120)
    private Integer age;

    @NotNull
    private String address;

    @Pattern(regexp = "^[\\w.%+-]+@[A-Za-z0-9.-]\\.[a-zA-Z\\.]{2,6}$")
    private String email;

    @NotNull
    @Pattern(regexp = "^0[1-9](?:[\\s]?[0-9]{2}){4}$")
    private String tel;

    @NotNull
    private Role role;

    private CooperativeDTO idCoop;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CooperativeDTO getIdCoop() {
        return idCoop;
    }

    public void setIdCoop(CooperativeDTO idCoop) {
        this.idCoop = idCoop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UtilisateurDTO)) {
            return false;
        }

        UtilisateurDTO utilisateurDTO = (UtilisateurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, utilisateurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UtilisateurDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", age=" + getAge() +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", role='" + getRole() + "'" +
            ", idCoop=" + getIdCoop() +
            "}";
    }
}
