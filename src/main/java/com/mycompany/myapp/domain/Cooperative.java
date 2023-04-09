package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cooperative.
 */
@Entity
@Table(name = "cooperative")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cooperative implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "zone", nullable = false)
    private String zone;

    @JsonIgnoreProperties(value = { "boutiques", "paniers", "courses", "idCoop" }, allowSetters = true)
    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Utilisateur idDG;

    @OneToMany(mappedBy = "idCoop")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "boutiques", "paniers", "courses", "idCoop" }, allowSetters = true)
    private Set<Utilisateur> utilisateurs = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_cooperative__boutique",
        joinColumns = @JoinColumn(name = "cooperative_id"),
        inverseJoinColumns = @JoinColumn(name = "boutique_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produits", "proprio", "cooperatives" }, allowSetters = true)
    private Set<Boutique> boutiques = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cooperative id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Cooperative name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return this.zone;
    }

    public Cooperative zone(String zone) {
        this.setZone(zone);
        return this;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Utilisateur getIdDG() {
        return this.idDG;
    }

    public void setIdDG(Utilisateur utilisateur) {
        this.idDG = utilisateur;
    }

    public Cooperative idDG(Utilisateur utilisateur) {
        this.setIdDG(utilisateur);
        return this;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        if (this.utilisateurs != null) {
            this.utilisateurs.forEach(i -> i.setIdCoop(null));
        }
        if (utilisateurs != null) {
            utilisateurs.forEach(i -> i.setIdCoop(this));
        }
        this.utilisateurs = utilisateurs;
    }

    public Cooperative utilisateurs(Set<Utilisateur> utilisateurs) {
        this.setUtilisateurs(utilisateurs);
        return this;
    }

    public Cooperative addUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
        utilisateur.setIdCoop(this);
        return this;
    }

    public Cooperative removeUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
        utilisateur.setIdCoop(null);
        return this;
    }

    public Set<Boutique> getBoutiques() {
        return this.boutiques;
    }

    public void setBoutiques(Set<Boutique> boutiques) {
        this.boutiques = boutiques;
    }

    public Cooperative boutiques(Set<Boutique> boutiques) {
        this.setBoutiques(boutiques);
        return this;
    }

    public Cooperative addBoutique(Boutique boutique) {
        this.boutiques.add(boutique);
        boutique.getCooperatives().add(this);
        return this;
    }

    public Cooperative removeBoutique(Boutique boutique) {
        this.boutiques.remove(boutique);
        boutique.getCooperatives().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cooperative)) {
            return false;
        }
        return id != null && id.equals(((Cooperative) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cooperative{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", zone='" + getZone() + "'" +
            "}";
    }
}
