import utilisateur from 'app/entities/utilisateur/utilisateur.reducer';
import cooperative from 'app/entities/cooperative/cooperative.reducer';
import produit from 'app/entities/produit/produit.reducer';
import panier from 'app/entities/panier/panier.reducer';
import boutique from 'app/entities/boutique/boutique.reducer';
import course from 'app/entities/course/course.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  utilisateur,
  cooperative,
  produit,
  panier,
  boutique,
  course,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
