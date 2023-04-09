package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    /**
     * Aucune clé primaire (id) n'est définie pour les relations\nen raison de la génération automaitque des clés primaires\ndes relations par Jhipster
     */
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Min(value = 18)
    @Max(value = 120)
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Pattern(regexp = "^[\\w.%+-]+@[A-Za-z0-9.-]\\.[a-zA-Z\\.]{2,6}$")
    @Column(name = "email")
    private String email;

    @NotNull
    @Pattern(regexp = "^0[1-9](?:[\\s]?[0-9]{2}){4}$")
    @Column(name = "tel", nullable = false)
    private String tel;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "proprio")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produits", "proprio", "cooperatives" }, allowSetters = true)
    private Set<Boutique> boutiques = new HashSet<>();

    @OneToMany(mappedBy = "idClient")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produits", "idClient" }, allowSetters = true)
    private Set<Panier> paniers = new HashSet<>();

    @OneToMany(mappedBy = "idCoursier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idPanier", "idCoursier" }, allowSetters = true)
    private Set<Course> courses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "idDG", "utilisateurs", "boutiques" }, allowSetters = true)
    private Cooperative idCoop;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Utilisateur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Utilisateur name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public Utilisateur age(Integer age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public Utilisateur address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public Utilisateur email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }

    public Utilisateur tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Role getRole() {
        return this.role;
    }

    public Utilisateur role(Role role) {
        this.setRole(role);
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Boutique> getBoutiques() {
        return this.boutiques;
    }

    public void setBoutiques(Set<Boutique> boutiques) {
        if (this.boutiques != null) {
            this.boutiques.forEach(i -> i.setProprio(null));
        }
        if (boutiques != null) {
            boutiques.forEach(i -> i.setProprio(this));
        }
        this.boutiques = boutiques;
    }

    public Utilisateur boutiques(Set<Boutique> boutiques) {
        this.setBoutiques(boutiques);
        return this;
    }

    public Utilisateur addBoutique(Boutique boutique) {
        this.boutiques.add(boutique);
        boutique.setProprio(this);
        return this;
    }

    public Utilisateur removeBoutique(Boutique boutique) {
        this.boutiques.remove(boutique);
        boutique.setProprio(null);
        return this;
    }

    public Set<Panier> getPaniers() {
        return this.paniers;
    }

    public void setPaniers(Set<Panier> paniers) {
        if (this.paniers != null) {
            this.paniers.forEach(i -> i.setIdClient(null));
        }
        if (paniers != null) {
            paniers.forEach(i -> i.setIdClient(this));
        }
        this.paniers = paniers;
    }

    public Utilisateur paniers(Set<Panier> paniers) {
        this.setPaniers(paniers);
        return this;
    }

    public Utilisateur addPanier(Panier panier) {
        this.paniers.add(panier);
        panier.setIdClient(this);
        return this;
    }

    public Utilisateur removePanier(Panier panier) {
        this.paniers.remove(panier);
        panier.setIdClient(null);
        return this;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        if (this.courses != null) {
            this.courses.forEach(i -> i.setIdCoursier(null));
        }
        if (courses != null) {
            courses.forEach(i -> i.setIdCoursier(this));
        }
        this.courses = courses;
    }

    public Utilisateur courses(Set<Course> courses) {
        this.setCourses(courses);
        return this;
    }

    public Utilisateur addCourse(Course course) {
        this.courses.add(course);
        course.setIdCoursier(this);
        return this;
    }

    public Utilisateur removeCourse(Course course) {
        this.courses.remove(course);
        course.setIdCoursier(null);
        return this;
    }

    public Cooperative getIdCoop() {
        return this.idCoop;
    }

    public void setIdCoop(Cooperative cooperative) {
        this.idCoop = cooperative;
    }

    public Utilisateur idCoop(Cooperative cooperative) {
        this.setIdCoop(cooperative);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        return id != null && id.equals(((Utilisateur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", age=" + getAge() +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
