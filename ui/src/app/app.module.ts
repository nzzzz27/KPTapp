import { BrowserModule }      from '@angular/platform-browser';
import { NgModule }           from '@angular/core';
import { HttpClientModule }   from '@angular/common/http';
import { MatIconModule }      from '@angular/material/icon';
import { MatCardModule }      from '@angular/material/card';
import { DragDropModule }     from '@angular/cdk/drag-drop';

import { AppRoutingModule }        from './app-routing.module';
import { AppComponent }            from './app.component';
import { BoardViewComponent }      from './board-view/board-view.component';
import { NavigationComponent }     from './navigation/navigation.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PanelsComponent }         from './board-view/panels/panels.component';
import { KeepState }               from './keep-service/keep.state';
import { NgxsModule }              from '@ngxs/store';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    BoardViewComponent,
    PanelsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatIconModule,
    MatCardModule,
    DragDropModule,
    BrowserAnimationsModule,
    NgxsModule.forRoot([ KeepState ]),
  ],
  providers: [],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
