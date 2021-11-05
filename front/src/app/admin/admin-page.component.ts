import {Component} from "@angular/core";
import {AppService} from "../root/app.service";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  templateUrl : 'admin-page.component.html'
})
export class AdminPageComponent{
  teams : Array<any> = [];
  constructor(private app: AppService, private http : HttpClient) {
    this.http.get(environment.apiUrl + '/team')
      .toPromise()
      .then((data : any) => {
        this.teams = <Array<any>>data;
      })
  }

}
