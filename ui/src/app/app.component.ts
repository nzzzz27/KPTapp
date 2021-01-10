import { Component, Injector } from '@angular/core';
import { createCustomElement } from '@angular/elements';
import { KeepComponent } from './keep/keep.component';

@Component({
  selector: 'app-hello',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent  {
  content = "aaa"
  constructor(
    private injector: Injector,
  ) {
    const AppKeepElement = createCustomElement(
      KeepComponent,
      { injector: this.injector }
    );
    customElements.define('app-hello', AppKeepElement);
  }

}
