import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/utilisateur">
        <Translate contentKey="global.menu.entities.utilisateur" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/cooperative">
        <Translate contentKey="global.menu.entities.cooperative" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/produit">
        <Translate contentKey="global.menu.entities.produit" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/panier">
        <Translate contentKey="global.menu.entities.panier" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/boutique">
        <Translate contentKey="global.menu.entities.boutique" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/course">
        <Translate contentKey="global.menu.entities.course" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
