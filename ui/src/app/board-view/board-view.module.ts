import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule }      from '@angular/material/card';
import { DragDropModule }     from '@angular/cdk/drag-drop';

import { CommonComponentModule }   from '../common-component/common-component.module';
import { BoardViewComponent }      from './board-view.component';
import { PanelsComponent }         from './panels/panels.component';

@NgModule({
  declarations: [
    BoardViewComponent,
    PanelsComponent,
  ],
  imports: [
    CommonModule,
    MatCardModule,
    DragDropModule,
    CommonComponentModule,
  ],
  exports: [
    BoardViewComponent,
  ],
})
export class BoardViewModule { }
