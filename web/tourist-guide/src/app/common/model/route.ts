import {Place} from './place';
import {UserAccount} from './user-account';

export class Route {
  routeId: number;
  name: string
  account: UserAccount;
  places: Place[];
}
