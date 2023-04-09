import { IBoutique } from 'app/shared/model/boutique.model';
import { IPanier } from 'app/shared/model/panier.model';
import { ICourse } from 'app/shared/model/course.model';
import { ICooperative } from 'app/shared/model/cooperative.model';
import { Role } from 'app/shared/model/enumerations/role.model';

export interface IUtilisateur {
  id?: number;
  name?: string;
  age?: number;
  address?: string;
  email?: string | null;
  tel?: string;
  role?: Role;
  boutiques?: IBoutique[] | null;
  paniers?: IPanier[] | null;
  courses?: ICourse[] | null;
  idCoop?: ICooperative | null;
}

export const defaultValue: Readonly<IUtilisateur> = {};
