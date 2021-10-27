import {AfterViewInit, Component} from '@angular/core';
import {AppService} from './app.service';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router'
import {Subscription} from 'rxjs';
import {environment} from "../environments/environment";

@Component({
  selector: 'confirmRegister.component',
  templateUrl: 'confirmRegister.component.html',
})
export class ConfirmRegisterComponent implements AfterViewInit{
  code: any;
  private querySubscription: Subscription;

  constructor(private app: AppService, private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.querySubscription = route.queryParams.subscribe(
      (queryParam: any) => {
        this.code = queryParam['code'];
      }
    );
  }
  ngAfterViewInit() {
    if(this.code == 125){
      // @ts-ignore
      document.getElementById(`bigText`).textContent
        = 'We are send an email to your mail. Follow the link to confirm registration'
    }
    else {
      this.router.navigateByUrl(environment.apiUrl + '/register/' + this.code)
    }
  }
}
