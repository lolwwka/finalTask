import {Component} from '@angular/core';
import {AppService} from '../root/app.service';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";


@Component({
  templateUrl: 'event.component.html'
})
export class EventComponent {
  profit = 0.05;
  firstIndex: any;
  secondIndex: any;
  error = ''
  exception: boolean;
  eventId: any;
  event = {firstTeam: '', secondTeam: '', firstTeamAmount: 0, secondTeamAmount: 0, tournament: ''}

  constructor(private app: AppService, private route: ActivatedRoute, private router: Router, private http: HttpClient) {
    let id = this.route.snapshot.paramMap.get('id')
    this.exception = false;
    this.eventId = id;
    app.getEvent(id, (data: any) => {
        this.event = data;
        this.setIndexes();
      },
      (error: any) => {
        router.navigateByUrl("/home");
      });
  }

  setBet(firstTeamBetValue: string, secondTeamBetValue: string) {
    if (firstTeamBetValue !== '' && secondTeamBetValue !== '') {
      this.error = 'You can bet only for 1 team';
      this.exception = true;
      return;
    }
    let teamName : any;
    let betValue : number;
    if(firstTeamBetValue !== ''){
      teamName = this.event.firstTeam;
      betValue = Number(firstTeamBetValue)
    } else {
      teamName = this.event.secondTeam;
      betValue = Number(secondTeamBetValue)
    }
    this.http.post(environment.apiUrl + "/bet", {
      teamName : teamName,
      betValue :betValue,
      eventId : this.eventId,
      userMail : this.app.userName
    })
      .toPromise()
      .then((data : any) => {
        if(data.exception == true){
          this.error = 'You already made 3 bets'
          this.exception = true;
          return;
        }
        this.event.firstTeamAmount = data.firstTeamAmount;
        this.event.secondTeamAmount = data.secondTeamAmount;
        this.setIndexes();
      })
      .catch((e) =>{
        this.error = e;
      });
    this.exception = false;
  }
  setIndexes(){
    let totalBets = this.event.firstTeamAmount + this.event.secondTeamAmount;
    let usersAmount = totalBets - this.profit * totalBets;
    if (this.event.firstTeamAmount == 0) {
      this.firstIndex = this.event.secondTeamAmount;
    }
    if (this.event.secondTeamAmount == 0) {
      this.secondIndex = this.event.firstTeamAmount;
    }
    if (this.event.firstTeamAmount == 0 && this.event.secondTeamAmount == 0) {
      this.firstIndex = 0;
      this.secondIndex = 0;
      return;
    }
    this.firstIndex = new Intl.NumberFormat('en-US',{maximumSignificantDigits: 3}).format(usersAmount / this.event.firstTeamAmount);
    this.secondIndex = new Intl.NumberFormat('en-US',{maximumSignificantDigits: 3}).format(usersAmount / this.event.secondTeamAmount);
  }
}
