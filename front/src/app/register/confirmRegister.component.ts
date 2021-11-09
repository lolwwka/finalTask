import {Component} from '@angular/core';
import {AppService} from '../root/app.service';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router'
import {Subscription} from 'rxjs';
import {environment} from "../../environments/environment";

@Component({
  templateUrl: 'confirmRegister.component.html',
})
export class ConfirmRegisterComponent {
  code: number = 0;
  private querySubscription: Subscription;
  Href = '';
  text = '';

  constructor(private app: AppService, private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.querySubscription = route.queryParams.subscribe(
      (queryParam: any) => {
        this.code = queryParam['code'];
      }
    );
    if (this.code == 125) {
      this.text = 'We are send an email to your mail. Follow the link to confirm registration'
    } else {
      this.http.post(environment.apiUrl + '/register/' + this.code, {})
        .toPromise()
        .then((data: any) => {
          if (data.result != false) {
            this.text = 'You have been registered'
          } else {
            this.text = 'Code is not active, you may be already verified'
          }
          this.Href = 'http://localhost:4200/login';
        });
    }
  }
}
