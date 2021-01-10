import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { APP_BASE_HREF} from '@angular/common';

import { AppComponent } from './app.component';
import { KeepComponent } from './keep/keep.component';

@NgModule({
  declarations: [
    AppComponent,
    KeepComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
