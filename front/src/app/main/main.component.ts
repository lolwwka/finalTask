import {Component} from '@angular/core';
import {AppService} from '../root/app.service';
import {AppComponent} from "../root/app.component";
import {PageEvent} from "@angular/material/paginator";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ActivatedRoute} from "@angular/router";

@Component({
  templateUrl: 'main.component.html'
})
export class MainComponent {
  Events: Array<any> = [];
  error = '';
  username = '';
  lowValue: number = 0;
  highValue: number = 20;
  winningTeam : any;

   constructor(private app: AppService, private appComponent: AppComponent, private http: HttpClient,private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.error = params.message;
    })
    this.username = appComponent.getUserLogin();
    app.getEvents((data: any) =>this.Events = <Array<any>>data,
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
  admin(){
    return this.appComponent.admin();
  }
  changeStatus(status : string, id : number, winningTeam : string){
    this.http.put(environment.apiUrl + "/event/" + id, {
      winningTeam : winningTeam,
      status : status
    }, {withCredentials: true})
      .toPromise()
      .then((data : any) =>{
        if(data.result != true){
          this.error = 'Error with changing status'
        }
      })
  }
}
