import {Component} from '@angular/core';
import {AppService} from '../root/app.service';
import {AppComponent} from "../root/app.component";
import {PageEvent} from "@angular/material/paginator";

@Component({
  templateUrl: 'main.component.html'
})
export class MainComponent {
  events: Array<any> = [];
  error = '';
  username = '';
  lowValue: number = 0;
  highValue: number = 20;

  constructor(private app: AppService, private appComponent: AppComponent) {
    this.username = appComponent.getUserLogin();
    app.getEvents((data: any) => this.events = <Array<any>>data,
      (error: any) => this.error = error.message);
  }

  authenticated() {
    return this.app.authenticated;
  }

  public getPaginatorData(event: PageEvent): PageEvent {
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
  }
}
