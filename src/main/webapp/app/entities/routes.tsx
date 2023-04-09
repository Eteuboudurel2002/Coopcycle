import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Utilisateur from './utilisateur';
import Cooperative from './cooperative';
import Produit from './produit';
import Panier from './panier';
import Boutique from './boutique';
import Course from './course';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="utilisateur/*" element={<Utilisateur />} />
        <Route path="cooperative/*" element={<Cooperative />} />
        <Route path="produit/*" element={<Produit />} />
        <Route path="panier/*" element={<Panier />} />
        <Route path="boutique/*" element={<Boutique />} />
        <Route path="course/*" element={<Course />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
