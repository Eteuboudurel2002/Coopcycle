import { IProduit } from 'app/shared/model/produit.model';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { ICooperative } from 'app/shared/model/cooperative.model';

export interface IBoutique {
  id?: number;
  name?: string;
  activity?: string | null;
  description?: string;
  address?: string;
  produits?: IProduit[] | null;
  proprio?: IUtilisateur;
  cooperatives?: ICooperative[] | null;
}

export const defaultValue: Readonly<IBoutique> = {};
