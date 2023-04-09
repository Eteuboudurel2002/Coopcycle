import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { IBoutique } from 'app/shared/model/boutique.model';

export interface ICooperative {
  id?: number;
  name?: string;
  zone?: string;
  idDG?: IUtilisateur;
  utilisateurs?: IUtilisateur[] | null;
  boutiques?: IBoutique[] | null;
}

export const defaultValue: Readonly<ICooperative> = {};
