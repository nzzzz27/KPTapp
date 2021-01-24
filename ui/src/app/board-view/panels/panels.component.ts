import { Component, OnInit } from '@angular/core';
import { Store, Select } from '@ngxs/store';
import {Observable} from 'rxjs';
import { Keep }              from '../../../model/keep';
import { KeepAction } from '../../keep-service/keep.actions';
import { KeepState } from '../../keep-service/keep.state';

@Component({
  selector:    'app-panels',
  templateUrl: './panels.component.html',
  styleUrls: [ './panels.component.scss' ]
})
export class PanelsComponent implements OnInit {

  @Select(KeepState.keeps) keeps$: Observable<Keep[]>

  constructor(
    private store: Store
  ) { }

  ngOnInit(): void {
    this.getKeeps();
  }

  getKeeps(): void {
    this.store.dispatch(new KeepAction.Load())
  }

}
