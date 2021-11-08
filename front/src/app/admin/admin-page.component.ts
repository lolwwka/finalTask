import {Component} from "@angular/core";
import {AppService} from "../root/app.service";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  templateUrl : 'admin-page.component.html'
})
export class AdminPageComponent{
  teams : Array<any> = [];
  firstTeamName : any;
  secondTeamName : any;
  eventException : boolean = true;
  teamException : boolean = true;
  constructor(private app: AppService, private http : HttpClient) {
    this.http.get(environment.apiUrl + '/team', {
      withCredentials: true
    })
      .toPromise()
      .then((data : any) => {
        this.teams = <Array<any>>data;
      })
  }
  createEvent(tournamentName : string){
    if (this.firstTeamName == this.secondTeamName){
      this.eventException = false;
      return;
    }
    this.http.post(environment.apiUrl + '/event', {
      firstTeamName : this.firstTeamName,
      secondTeamName : this.secondTeamName,
      tournamentName : tournamentName
    }, {
      withCredentials: true
    })
      .toPromise()
      .then((data : any) =>{
        this.eventException = data.result == 'true';
      })
  }
  createTeam(addingTeamName : string){
    this.http.post(environment.apiUrl + '/team',{
      name : addingTeamName
    },{
      withCredentials: true
    })
      .toPromise()
      .then((data : any) =>{
        this.teamException = data.confirmed == 'true';
      })
  }
  getEventException(){
    return this.eventException;
  }
  getTeamException(){
    return this.teamException;
  }
}
