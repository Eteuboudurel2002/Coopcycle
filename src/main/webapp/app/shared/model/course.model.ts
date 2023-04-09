import dayjs from 'dayjs';
import { IPanier } from 'app/shared/model/panier.model';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';

export interface ICourse {
  id?: number;
  echeance?: string;
  dateCreation?: string;
  statut?: string;
  idPanier?: IPanier;
  idCoursier?: IUtilisateur;
}

export const defaultValue: Readonly<ICourse> = {};
