import { Component, OnInit } from '@angular/core';
import { Store, Select } from '@ngxs/store';
import {Observable} from 'rxjs';
import { Keep }          from '../../../model/keep';
import { Problem }       from '../../../model/problem';
import { Try }           from '../../../model/try';
import { KeepAction }    from '../../keep-service/keep.actions';
import { ProblemAction } from '../../problem-service/problem.actions';
import { TryAction }     from '../../try-service/try.actions';
import { KeepState }    from '../../keep-service/keep.state';
import { ProblemState } from '../../problem-service/problem.state';
import { TryState }     from '../../try-service/try.state';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector:    'app-panels',
  templateUrl: './panels.component.html',
  styleUrls: [ './panels.component.scss' ]
})
export class PanelsComponent implements OnInit {

  constructor(
    private store: Store
  ) { }

  @Select(KeepState.keeps)       keeps$:    Observable<Keep[]>
  @Select(ProblemState.problems) problems$: Observable<Problem[]>
  @Select(TryState.try)          try$:      Observable<Try[]>

  ngOnInit(): void {
    this.getKpt();
  }

  getKpt(): void {
    this.store.dispatch(new KeepAction.Load())
    this.store.dispatch(new ProblemAction.Load())
    this.store.dispatch(new TryAction.Load())
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
    }
  }

}
