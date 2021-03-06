import { Component, OnInit } from '@angular/core';
import { Store, Select } from '@ngxs/store';
import { Observable }    from 'rxjs';
// import { map, tap } from 'rxjs/operators';
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

  drop(event: CdkDragDrop<Observable<Keep[] | Problem[] | Try[]>>) {
    if (event.previousContainer === event.container) {
      event.container.data.subscribe((data) =>
        moveItemInArray(data, event.previousIndex, event.currentIndex)
      )
    } else {
      event.previousContainer.data.subscribe(prevData => {
        event.container.data.subscribe(data =>
          transferArrayItem(prevData, data, event.previousIndex, event.currentIndex)
        )
      });
    }
  }

}
