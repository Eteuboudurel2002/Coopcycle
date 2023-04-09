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
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Produit implements Serializable {

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
    @Column(name = "prix", nullable = false)
    private Integer prix;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "produits", "proprio", "cooperatives" }, allowSetters = true)
    private Boutique idboutique;

    @ManyToMany(mappedBy = "produits")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produits", "idClient" }, allowSetters = true)
    private Set<Panier> paniers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Produit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Produit name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrix() {
        return this.prix;
    }

    public Produit prix(Integer prix) {
        this.setPrix(prix);
        return this;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Boutique getIdboutique() {
        return this.idboutique;
    }

    public void setIdboutique(Boutique boutique) {
        this.idboutique = boutique;
    }

    public Produit idboutique(Boutique boutique) {
        this.setIdboutique(boutique);
        return this;
    }

    public Set<Panier> getPaniers() {
        return this.paniers;
    }

    public void setPaniers(Set<Panier> paniers) {
        if (this.paniers != null) {
            this.paniers.forEach(i -> i.removeProduit(this));
        }
        if (paniers != null) {
            paniers.forEach(i -> i.addProduit(this));
        }
        this.paniers = paniers;
    }

    public Produit paniers(Set<Panier> paniers) {
        this.setPaniers(paniers);
        return this;
    }

    public Produit addPanier(Panier panier) {
        this.paniers.add(panier);
        panier.getProduits().add(this);
        return this;
    }

    public Produit removePanier(Panier panier) {
        this.paniers.remove(panier);
        panier.getProduits().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", prix=" + getPrix() +
            "}";
    }
}
