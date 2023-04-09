package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    /**
     * Objet créer lors de la validation d'un panier
     */
    @NotNull
    @Column(name = "echeance", nullable = false)
    private String echeance;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @NotNull
    @Column(name = "statut", nullable = false)
    private String statut;

    @JsonIgnoreProperties(value = { "produits", "idClient" }, allowSetters = true)
    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Panier idPanier;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "boutiques", "paniers", "courses", "idCoop" }, allowSetters = true)
    private Utilisateur idCoursier;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Course id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEcheance() {
        return this.echeance;
    }

    public Course echeance(String echeance) {
        this.setEcheance(echeance);
        return this;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public Course dateCreation(LocalDate dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatut() {
        return this.statut;
    }

    public Course statut(String statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Panier getIdPanier() {
        return this.idPanier;
    }

    public void setIdPanier(Panier panier) {
        this.idPanier = panier;
    }

    public Course idPanier(Panier panier) {
        this.setIdPanier(panier);
        return this;
    }

    public Utilisateur getIdCoursier() {
        return this.idCoursier;
    }

    public void setIdCoursier(Utilisateur utilisateur) {
        this.idCoursier = utilisateur;
    }

    public Course idCoursier(Utilisateur utilisateur) {
        this.setIdCoursier(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", echeance='" + getEcheance() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", statut='" + getStatut() + "'" +
            "}";
    }
}
