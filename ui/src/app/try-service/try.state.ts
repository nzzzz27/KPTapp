import { Injectable } from '@angular/core';
import { Selector, State, Action, StateContext } from '@ngxs/store';
import { tap } from 'rxjs/operators';
import { Try } from '../../model/try';
import { TryAction } from './try.actions';
import { TryService } from './try.service';

export class TryStateModel {
  try: Try[];
}

@Injectable()
@State<TryStateModel>({
  name: 'try',
  defaults: {
    try: [],
  },
})

export class TryState {

  constructor(
    private tryService: TryService
  ) {}

  @Selector()
  static try(state: TryStateModel) {
    return state.try;
  }

  @Action(TryAction.Load)
  load(ctx: StateContext<TryStateModel>) {
    return this.tryService.getTry()
      .pipe(
        tap((data) => {
          ctx.patchState({
            try: data
          })
        })
      )
  }
}
