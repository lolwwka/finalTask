import {Component} from "@angular/core";
import {AppService} from "../root/app.service";
import {AppComponent} from "../root/app.component";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  templateUrl: 'profile.component.html'
})
export class ProfileComponent {
  userName: any;
  balance : number = 0;
  bets : Array<any> = [];

  constructor(private app: AppService, private appComponent: AppComponent,private router: Router, private http: HttpClient) {
    this.userName = appComponent.getUserName();
    http.get(environment.apiUrl + "/user/" + this.userName)
      .toPromise()
      .then((data : any) => {
        this.balance = data.balance;
        this.bets = data.bets;
      })
  }
  authenticated() {
    return this.app.authenticated;
  }
}
