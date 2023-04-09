import { IBoutique } from 'app/shared/model/boutique.model';
import { IPanier } from 'app/shared/model/panier.model';

export interface IProduit {
  id?: number;
  name?: string;
  prix?: number;
  idboutique?: IBoutique;
  paniers?: IPanier[] | null;
}

export const defaultValue: Readonly<IProduit> = {};
