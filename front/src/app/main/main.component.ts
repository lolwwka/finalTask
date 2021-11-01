import {Component} from '@angular/core';
import {AppService} from '../root/app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'main.component',
  templateUrl: 'main.component.html',
})
export class MainComponent {
  events : Array<any> = [];
  error = '';

  constructor(private app: AppService) {
    app.getEvents((data: any) => this.events = <Array<any>>data,
      (error: any) => this.error = error.message);
  }

  authenticated() {
    return this.app.authenticated;
  }
}
