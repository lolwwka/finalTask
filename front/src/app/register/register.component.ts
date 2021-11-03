import {Component} from '@angular/core';
import {AppService} from '../root/app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from "../../environments/environment";

@Component({
  selector: 'register.component',
  templateUrl: 'register.component.html'
})
export class RegisterComponent {
  error = false;


  credentials = {
    email: '',
    password: ''
  };

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  register() {
    this.http.post(environment.apiUrl + "/register", {
      email: this.credentials.email,
      password: this.credentials.password
    })
      .toPromise()
      .then((data: any) => {
        if (data.result == true) {
          this.router.navigateByUrl("/confirm?code=125")
        }
      })
      .catch(() => {
        this.error = true;
      });
  }
}
