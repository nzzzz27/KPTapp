import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule }      from '@angular/material/icon';

import { NavigationComponent }     from './navigation/navigation.component';

@NgModule({
  declarations: [
    NavigationComponent,
  ],
  imports: [
    CommonModule,
    MatIconModule,
  ],
  exports: [
    NavigationComponent,
  ]
})
export class CommonComponentModule { }
