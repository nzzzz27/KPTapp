import { Keep } from '../../model/keep';

export module KeepAction {
  export const LOAD_KEEP = 'Load_Keep';
  export const ADD_KEEP = 'Add_Keep';

  export class Load {
    static readonly type = LOAD_KEEP;
  }

  export class Add {
    static readonly type = ADD_KEEP;
    constructor(public payload: Keep) {}
  }
}
