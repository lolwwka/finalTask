import {Component} from "@angular/core";
import {AppService} from "../root/app.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  templateUrl : 'balance.component.html'
})
export class BalanceComponent{
  userBalance : any;
  constructor(private app: AppService, private http : HttpClient) {
    this.userBalance = app.balance;
  }
  authenticated() {
    return this.app.authenticated;
  }
  getFreeMoney(){
    const headers = new HttpHeaders({'Login': this.app.userName});
    this.http.post(environment.apiUrl + '/balance', {}, {headers : headers, withCredentials:true})
      .toPromise();
    this.userBalance +=100;
  }
}
