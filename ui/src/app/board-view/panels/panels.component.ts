import { Component, OnInit } from '@angular/core';
import { CdkDragDrop, moveItemInArray,
         transferArrayItem } from '@angular/cdk/drag-drop';
import { MatDialog }         from '@angular/material/dialog';
import { Store, Select }   from '@ngxs/store';
import { Observable }      from 'rxjs';
import { Keep }            from '../../../model/keep';
import { Problem }         from '../../../model/problem';
import { Try }             from '../../../model/try';
import { KeepAction }      from '../../keep-service/keep.actions';
import { ProblemAction }   from '../../problem-service/problem.actions';
import { TryAction }       from '../../try-service/try.actions';
import { KeepState }       from '../../keep-service/keep.state';
import { ProblemState }    from '../../problem-service/problem.state';
import { TryState }        from '../../try-service/try.state';
import { DialogComponent } from '../dialog/dialog.component'

@Component({
  selector:    'app-panels',
  templateUrl: './panels.component.html',
  styleUrls: [ './panels.component.scss' ]
})
export class PanelsComponent implements OnInit {

  constructor(
    private store: Store,
    public dialog: MatDialog
  ) { }

  newKeep: string;
  @Select(KeepState.keeps)        keeps$:    Observable<Keep[]>
  @Select(ProblemState.problems)  problems$: Observable<Problem[]>
  @Select(TryState.try)           try$:      Observable<Try[]>

  ngOnInit(): void {
    this.getKpt();
  }

  getKpt(): void {
    this.store.dispatch(new KeepAction.Load())
    this.store.dispatch(new ProblemAction.Load())
    this.store.dispatch(new TryAction.Load())
  }

  addKeep(data): void {
    this.store.dispatch(new KeepAction.Add(data as Keep))
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

  animal: string;
  name: string
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      id:        'dialog-open',
      minWidth:  '500px',
      minHeight: '500px',
      // position: {
      //   top: '50%',
      //   left: '50%'
      // },
      data:   {name: this.name, animal: this.animal},
    });
    dialogRef.afterClosed().subscribe(result => {
      this.name = result
      console.log(`Dialog result: ${result}`);
      console.log(result);
    });
  }
}
