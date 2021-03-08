import { Injectable } from '@angular/core';
import { Selector, State, Action, StateContext } from '@ngxs/store';
import { tap, finalize } from 'rxjs/operators';
import { Keep } from '../../model/keep';
import { KeepAction } from './keep.actions';
import { KeepService } from './keep.service';

export class KeepStateModel {
  keeps: Keep[];
}

@Injectable()
@State<KeepStateModel>({
  name: 'keeps',
  defaults: {
    keeps: [],
  },
})

export class KeepState {

  constructor(
    private keepService: KeepService
  ) {}

  @Selector()
  static keeps(state: KeepStateModel) {
    return state.keeps;
  }

  @Action(KeepAction.Load)
  load(ctx: StateContext<KeepStateModel>) {
    return this.keepService.getKeeps()
      .pipe(
        tap((data) => {
          ctx.patchState({
            keeps: data
          })
        })
      )
  }

  @Action(KeepAction.Add)
  post(ctx: StateContext<KeepStateModel>, action: KeepAction.Add) {
    const keep = action.payload
    return this.keepService.add(keep).pipe(
      finalize(() => {
        ctx.dispatch(new KeepAction.Load)
      })
    )
  }

}
