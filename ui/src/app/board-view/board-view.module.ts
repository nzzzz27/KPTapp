import { NgModule }           from '@angular/core';
import { CommonModule }       from '@angular/common';
import { MatDialogModule }    from '@angular/material/dialog';
import { MatCardModule }      from '@angular/material/card';
import { DragDropModule }     from '@angular/cdk/drag-drop';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule }     from '@angular/material/input';

import { CommonComponentModule }   from '../common-component/common-component.module';
import { BoardViewComponent }      from './board-view.component';
import { PanelsComponent }         from './panels/panels.component';
import { DialogComponent }         from './dialog/dialog.component';

@NgModule({
  declarations: [
    BoardViewComponent,
    PanelsComponent,
    DialogComponent,
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatDialogModule,
    DragDropModule,
    MatFormFieldModule,
    MatInputModule,
    CommonComponentModule,
  ],
  exports: [
    BoardViewComponent,
  ],
})

export class BoardViewModule { }
