import { Component, HostListener, HostBinding } from '@angular/core';

@Component({
  selector: 'app-keep',
  templateUrl: './keep.component.html',
  styleUrls: ['./keep.component.scss'],
})

export class KeepComponent {
  constructor() { }

  name: string = 'HELOOOOOOOOOO';

  @HostBinding('style.background-color') color = 'white';
  @HostListener('mouseover') onclick() {
    this.color = '#eee';
  }
  @HostListener('mouseout') onmouseout() {
    this.color = 'white';
  }

}
