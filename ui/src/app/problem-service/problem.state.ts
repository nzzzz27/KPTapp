import { Injectable } from '@angular/core';
import { Selector, State, Action, StateContext } from '@ngxs/store';
import { tap } from 'rxjs/operators';
import { Problem } from '../../model/problem';
import { ProblemAction } from './problem.actions';
import { ProblemService } from './problem.service';

export class ProblemStateModel {
  problems: Problem[];
}

@Injectable()
@State<ProblemStateModel>({
  name: 'problems',
  defaults: {
    problems: [],
  },
})

export class ProblemState {

  constructor(
    private problemService: ProblemService
  ) {}

  @Selector()
  static problems(state: ProblemStateModel) {
    return state.problems;
  }

  @Action(ProblemAction.Load)
  load(ctx: StateContext<ProblemStateModel>) {
    return this.problemService.getProblems()
      .pipe(
        tap((data) => {
          ctx.patchState({
            problems: data
          })
        })
      )
  }
}
