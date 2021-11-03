import {Component} from '@angular/core';
import {AppService} from '../root/app.service';
import {AppComponent} from "../root/app.component";

@Component({
  templateUrl: 'main.component.html'
})
export class MainComponent {
  events: Array<any> = [];
  error = '';
  username = '';

  constructor(private app: AppService, private appComponent: AppComponent) {
    this.username = appComponent.getUserName();
    app.getEvents((data: any) => this.events = <Array<any>>data,
      (error: any) => this.error = error.message);
  }

  authenticated() {
    return this.app.authenticated;
  }
}
