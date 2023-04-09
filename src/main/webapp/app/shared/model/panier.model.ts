import dayjs from 'dayjs';
import { IProduit } from 'app/shared/model/produit.model';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';

export interface IPanier {
  id?: number;
  dateCreation?: string;
  dateModification?: string;
  statut?: string;
  total?: number;
  produits?: IProduit[] | null;
  idClient?: IUtilisateur;
}

export const defaultValue: Readonly<IPanier> = {};
