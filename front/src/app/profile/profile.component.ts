import {Component} from "@angular/core";
import {AppService} from "../root/app.service";
import {AppComponent} from "../root/app.component";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {PageEvent} from "@angular/material/paginator";

@Component({
  templateUrl: 'profile.component.html'
})
export class ProfileComponent {
  userName: any;
  balance : number = 0;
  bets : Array<any> = [];
  lowValue: number = 0;
  highValue: number = 4;

  constructor(private app: AppService, private appComponent: AppComponent,private router: Router, private http: HttpClient) {
    this.userName = appComponent.getUserLogin();
    http.get(environment.apiUrl + "/user/" + appComponent.getUserLogin(),{
      withCredentials: true
    })
      .toPromise()
      .then((data : any) => {
        this.balance = data.balance;
        this.app.balance = this.balance;
        this.bets = <Array<any>>data.bets;
      })
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
