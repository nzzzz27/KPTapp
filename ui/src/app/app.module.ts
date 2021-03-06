import { BrowserModule }      from '@angular/platform-browser';
import { NgModule }           from '@angular/core';
import { HttpClientModule }   from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule }        from './app-routing.module';
import { AppComponent }            from './app.component';
import { BoardViewModule }         from './board-view/board-view.module';

import { KeepState }               from './keep-service/keep.state';
import { ProblemState }            from './problem-service/problem.state';
import { TryState }                from './try-service/try.state';
import { NgxsModule }              from '@ngxs/store';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BoardViewModule,
    NgxsModule.forRoot([ KeepState, ProblemState, TryState ]),
  ],
  providers: [],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
