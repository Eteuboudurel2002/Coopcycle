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
 * A Boutique.
 */
@Entity
@Table(name = "boutique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Boutique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "activity")
    private String activity;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "idboutique")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idboutique", "paniers" }, allowSetters = true)
    private Set<Produit> produits = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "boutiques", "paniers", "courses", "idCoop" }, allowSetters = true)
    private Utilisateur proprio;

    @ManyToMany(mappedBy = "boutiques")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idDG", "utilisateurs", "boutiques" }, allowSetters = true)
    private Set<Cooperative> cooperatives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Boutique id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Boutique name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return this.activity;
    }

    public Boutique activity(String activity) {
        this.setActivity(activity);
        return this;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return this.description;
    }

    public Boutique description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return this.address;
    }

    public Boutique address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(Set<Produit> produits) {
        if (this.produits != null) {
            this.produits.forEach(i -> i.setIdboutique(null));
        }
        if (produits != null) {
            produits.forEach(i -> i.setIdboutique(this));
        }
        this.produits = produits;
    }

    public Boutique produits(Set<Produit> produits) {
        this.setProduits(produits);
        return this;
    }

    public Boutique addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setIdboutique(this);
        return this;
    }

    public Boutique removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setIdboutique(null);
        return this;
    }

    public Utilisateur getProprio() {
        return this.proprio;
    }

    public void setProprio(Utilisateur utilisateur) {
        this.proprio = utilisateur;
    }

    public Boutique proprio(Utilisateur utilisateur) {
        this.setProprio(utilisateur);
        return this;
    }

    public Set<Cooperative> getCooperatives() {
        return this.cooperatives;
    }

    public void setCooperatives(Set<Cooperative> cooperatives) {
        if (this.cooperatives != null) {
            this.cooperatives.forEach(i -> i.removeBoutique(this));
        }
        if (cooperatives != null) {
            cooperatives.forEach(i -> i.addBoutique(this));
        }
        this.cooperatives = cooperatives;
    }

    public Boutique cooperatives(Set<Cooperative> cooperatives) {
        this.setCooperatives(cooperatives);
        return this;
    }

    public Boutique addCooperative(Cooperative cooperative) {
        this.cooperatives.add(cooperative);
        cooperative.getBoutiques().add(this);
        return this;
    }

    public Boutique removeCooperative(Cooperative cooperative) {
        this.cooperatives.remove(cooperative);
        cooperative.getBoutiques().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Boutique)) {
            return false;
        }
        return id != null && id.equals(((Boutique) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Boutique{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", activity='" + getActivity() + "'" +
            ", description='" + getDescription() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
