import { Component, OnInit } from '@angular/core';
import { Store, Select } from '@ngxs/store';
import {Observable} from 'rxjs';
import { Keep }              from '../../../model/keep';
import { KeepAction } from '../../keep-service/keep.actions';
import { KeepState } from '../../keep-service/keep.state';
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

  @Select(KeepState.keeps) keeps$: Observable<Keep[]>

  ngOnInit(): void {
    this.getKeeps();
  }
  todo = [
    'Get to work',
    'Pick up groceries',
    'Go home',
    'Fall asleep'
  ];
  getKeeps(): void {
    this.store.dispatch(new KeepAction.Load())
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
